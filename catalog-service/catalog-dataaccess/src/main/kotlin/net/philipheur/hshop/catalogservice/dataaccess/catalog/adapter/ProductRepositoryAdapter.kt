package net.philipheur.hshop.catalogservice.dataaccess.catalog.adapter

import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.*
import net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper.BrandDataaccessMapper
import net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper.CategoryDataaccessMapper
import net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper.ProductDataAccessMapper
import net.philipheur.hshop.catalogservice.dataaccess.catalog.repository.CategoryJpaRepository
import net.philipheur.hshop.catalogservice.dataaccess.catalog.repository.ProductJpaRepository
import net.philipheur.hshop.catalogservice.domain.core.entity.Product
import net.philipheur.hshop.catalogservice.domain.service.dto.PageResponse
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.ProductRepository
import net.philipheur.hshop.common.domain.exception.DomainException
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductRepositoryAdapter(
    private val productRepository: ProductJpaRepository,
    private val categoryRepository: CategoryJpaRepository,
    private val productDataAccessMapper: ProductDataAccessMapper,
    private val brandDataaccessMapper: BrandDataaccessMapper,
    private val categoryDataaccessMapper: CategoryDataaccessMapper
) : ProductRepository {

    private val log by LoggerDelegator()

    override fun findAllByKeywordFullText(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String
    ): PageResponse<Product> {

        val entities = if (keyword.isNotEmpty()) {
            productRepository.findAllWithKeywordFullText(
                keyword = keyword,
                pageable = pageRequest(
                    pageNo = pageNo,
                    pageSize = pageSize,
                    sortBy = sortBy,
                    orderBy = orderBy,
                )
            )
        } else {
            productRepository.findAllWithKeyword(
                keyword = keyword,
                pageable = pageRequest(
                    pageNo = pageNo,
                    pageSize = pageSize,
                    sortBy = sortBy,
                    orderBy = orderBy,
                )
            )
        }

        return PageResponse(
            content = entities.map {
                productDataAccessMapper.transformProductEntityToDomain(it)
            }.toList(),
            pageNo = entities.number,
            pageSize = entities.size,
            totalElements = entities.totalElements,
            totalPages = entities.totalPages,
            last = entities.isLast
        )
    }

    override fun findAllByKeyword(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String
    ): PageResponse<Product> {

        val entities = productRepository.findAllWithKeyword(
            keyword = keyword,
            pageable = pageRequest(
                pageNo = pageNo,
                pageSize = pageSize,
                sortBy = sortBy,
                orderBy = orderBy,
            )
        )

        return PageResponse(
            content = entities.map {
                productDataAccessMapper.transformProductEntityToDomain(it)
            }.toList(),
            pageNo = entities.number,
            pageSize = entities.size,
            totalElements = entities.totalElements,
            totalPages = entities.totalPages,
            last = entities.isLast
        )
    }


    override fun findByCategoryId(
        categoryId: UUID,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
    ): PageResponse<Product> {
        val categoryOptional = categoryRepository.findById(categoryId)
        if (!categoryOptional.isPresent) {
            throw DomainException("category with id $categoryId not found")
        }

        val categoryEntity = categoryOptional.get()
        val leafCategories = mutableListOf<CategoryEntity>()

        // 하위 카테고리를 중에 leaf 만 모은다
        val sublist = mutableListOf(categoryEntity)
        while (sublist.isNotEmpty()) {
            val node = sublist.removeAt(0)

            if (node.subCategories.size == 0) leafCategories.add(node)
            else sublist.addAll(node.subCategories)
        }

        log.info("leaf node $leafCategories")

        val productEntityList = productRepository.findByCategories(
            categories = leafCategories,
            pageable = pageRequest(
                pageNo = pageNo,
                pageSize = pageSize,
                sortBy = sortBy,
                orderBy = orderBy,
            )
        )

        return PageResponse(
            content = productEntityList.map {
                productDataAccessMapper.transformProductEntityToDomain(it)
            }.toList(),
            pageNo = productEntityList.number,
            pageSize = productEntityList.size,
            totalElements = productEntityList.totalElements,
            totalPages = productEntityList.totalPages,
            last = productEntityList.isLast
        )
    }

    override fun findById(id: UUID): Product {
        val entityOptional = productRepository.findById(id)
        if (!entityOptional.isPresent) {
            throw RuntimeException("product id with $id not found")
        }

        val entity = entityOptional.get()

        return productDataAccessMapper.transformProductEntityToDomain(entity)
    }

    override fun createProduct(product: Product): Product {
        val productEntity = ProductEntity(
                id = product.id!!.value,
                name = product.name,
                alias = product.alias,
                shortDescription = product.shortDescription,
                fullDescription = product.fullDescription,
                status = product.status,
                mainImage = product.mainImage,
                discountPercent = product.discountPercent,
                price = product.price.amount,
                cost = product.cost.amount,
                length = product.dimension.length,
                width = product.dimension.width,
                height = product.dimension.height,
                weight = product.dimension.weight,
                brand = brandDataaccessMapper
                    .transformBrandDomainToEntity(product.brand),
                category = categoryDataaccessMapper
                    .transformCategoryDomainToEntity(product.category),
                details = product.details.map {
                    ProductDetailEntity(
                        id = it.id,
                        name = it.name,
                        value = it.value,
                        product = productDataAccessMapper
                            .transformProductDomainToEntity(product)
                    )
                }.toMutableSet(),
                detailImages = product.detailImages.map {
                    ProductImageEntity(
                        id = it.id,
                        name = it.name,
                        product = productDataAccessMapper
                            .transformProductDomainToEntity(product)
                    )
                }.toMutableSet(),
                createdAt = product.createdAt,
            )

        productRepository.save(productEntity)

        return productDataAccessMapper
            .transformProductEntityToDomain(productEntity)
    }

    private fun pageRequest(
        pageNo: Int,
        pageSize: Int,
        orderBy: String,
        sortBy: String
    ) = PageRequest.of(
        pageNo,
        pageSize,
        if (orderBy.equals(
                Sort.Direction.ASC.name, true
            )
        ) Sort.by(sortBy).ascending()
        else Sort.by(sortBy).descending()
    )

}