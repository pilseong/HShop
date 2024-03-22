package net.philipheur.hshop.catalogservice.domain.service

import net.philipheur.hshop.catalogservice.domain.core.entity.Inventory
import net.philipheur.hshop.catalogservice.domain.core.entity.Product
import net.philipheur.hshop.catalogservice.domain.service.dto.CreateProductCommand
import net.philipheur.hshop.catalogservice.domain.service.dto.ProductDto
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.catalogservice.domain.service.mapper.CatalogDomainServiceMapper
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.ProductService
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.BrandRepository
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.InventoryRepository
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.ProductRepository
import net.philipheur.hshop.common.domain.dtos.settings.SettingDto
import net.philipheur.hshop.common.domain.valueobject.*
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.*

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val brandRepository: BrandRepository,
    private val inventoryRepository: InventoryRepository,
    private val mapper: CatalogDomainServiceMapper
) : ProductService {
    override fun findAllProductsByKeyword(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        settings: List<SettingDto>
    ): SearchResponse<ProductDto> {
        val pageResponse = productRepository.findAllByKeyword(
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
            keyword = query
        )

        return SearchResponse(
            totalElements = pageResponse.totalElements,
            totalPages = pageResponse.totalPages,
            pageNo = pageResponse.pageNo,
            pageSize = pageResponse.pageSize,
            last = pageResponse.last,
            content = pageResponse.content.map {
                mapper.transformProductDomainToDto(it, settings)
            }
        )
    }

    override fun findAllProductsByKeywordFullText(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        settings: List<SettingDto>
    ): SearchResponse<ProductDto> {
        val pageResponse = productRepository.findAllByKeywordFullText(
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
            keyword = query
        )

        return SearchResponse(
            totalElements = pageResponse.totalElements,
            totalPages = pageResponse.totalPages,
            pageNo = pageResponse.pageNo,
            pageSize = pageResponse.pageSize,
            last = pageResponse.last,
            content = pageResponse.content.map {
                mapper.transformProductDomainToDto(it, settings)
            }
        )
    }

    override fun findByCategoryId(
        categoryId: UUID,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        settings: List<SettingDto>
    ): SearchResponse<ProductDto> {
        val pageResponse = productRepository.findByCategoryId(
            categoryId = categoryId,
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
        )

        return SearchResponse(
            totalElements = pageResponse.totalElements,
            totalPages = pageResponse.totalPages,
            pageNo = pageResponse.pageNo,
            pageSize = pageResponse.pageSize,
            last = pageResponse.last,
            content = pageResponse.content.map {
                mapper.transformProductDomainToDto(it, settings)
            }
        )
    }

    override fun findById(
        id: UUID,
        settings: List<SettingDto>
    ): ProductDto {
        val product = productRepository.findById(id)

        return mapper.transformProductDomainToDto(product, settings)

    }

    override fun createProduct(command: CreateProductCommand) {
        // 제품이 중복되는지 확인 -> 불가능

        // 브랜드, 카테고리 정보를 가지고 온다.
        val brand = brandRepository.findById(BrandId(command.brandId))
        val category = brand.categories.first { it.id!!.value == command.categoryId}


        // 제품 생성
        var product = Product(
            id = ProductId(UUID.randomUUID()),
            name = command.name,
            alias = command.alias,
            status = command.status,
            brand = brand,
            category = category,
            cost = Money(command.cost),
            price = Money(command.price),
            discountPercent = command.discountPercent,
            dimension = Dimension(0.0, 0.0, 0.0, 0.0),
            mainImage = "",
            fullDescription = "",
            shortDescription = "",
            createdAt = ZonedDateTime.now(),
        )

        product = productRepository.createProduct(product)

        // inventory 를 생성한다.

        val inventory = Inventory(
            inventoryId = InventoryId(UUID.randomUUID()),
            product = product,
            amount = 0
        )

        inventoryRepository.createInventory(inventory)
    }
}