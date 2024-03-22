package net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper

import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.*
import net.philipheur.hshop.catalogservice.domain.core.entity.Brand
import net.philipheur.hshop.catalogservice.domain.core.entity.Category
import net.philipheur.hshop.catalogservice.domain.core.entity.Inventory
import net.philipheur.hshop.catalogservice.domain.core.entity.Product
import net.philipheur.hshop.catalogservice.domain.core.valueobject.ProductDetail
import net.philipheur.hshop.catalogservice.domain.core.valueobject.ProductImage
import net.philipheur.hshop.common.domain.valueobject.*
import org.springframework.stereotype.Component

@Component
class ProductDataAccessMapper(
    private val brandDataaccessMapper: BrandDataaccessMapper,
    private val categoryDataaccessMapper: CategoryDataaccessMapper
) {
    fun transformProductDomainToEntity(
        domain: Product
    ): ProductEntity = ProductEntity(
        id = domain.id!!.value,
        name = domain.name,
        alias = domain.alias,
        shortDescription = domain.shortDescription,
        fullDescription = domain.fullDescription,
        status = domain.status,
        mainImage = domain.mainImage,
        discountPercent = domain.discountPercent,
        price = domain.price.amount,
        cost = domain.cost.amount,
        length = domain.dimension.length,
        width = domain.dimension.width,
        height = domain.dimension.height,
        weight = domain.dimension.weight,
        brand = brandDataaccessMapper
            .transformBrandDomainToEntity(domain.brand),
        category = categoryDataaccessMapper
            .transformCategoryDomainToEntity(domain.category),
        details = domain.details.map {
            ProductDetailEntity(
                id = it.id,
                name = it.name,
                value = it.value,
                product = transformProductDomainToEntity(domain)
            )
        }.toMutableSet(),
        detailImages = domain.detailImages.map {
            ProductImageEntity(
                id = it.id,
                name = it.name,
                product = transformProductDomainToEntity(domain)
            )
        }.toMutableSet(),
        inventory = if (domain.inventory != null)
            transformInventoryDomainToEntity(domain.inventory!!) else null,
        createdAt = domain.createdAt,
    )

    fun transformProductEntityToDomain(
        entity: ProductEntity
    ): Product = Product(
        id = ProductId(entity.id),
        name = entity.name,
        alias = entity.alias,
        shortDescription = entity.shortDescription,
        fullDescription = entity.fullDescription,
        status = entity.status,
        mainImage = entity.mainImage,
        discountPercent = entity.discountPercent,
        price = Money(entity.price),
        cost = Money(entity.cost),
        dimension = Dimension(
            length = entity.length,
            width = entity.width,
            height = entity.height,
            weight = entity.weight
        ),
        brand = brandDataaccessMapper
            .transformBrandEntityToDomain(entity.brand),
        category = brandDataaccessMapper
            .transformCategoryEntityToDomain(entity.category),
        details = entity.details.map {
            ProductDetail(
                id = it.id,
                name = it.name,
                value = it.value
            )
        },
        detailImages = entity.detailImages.map {
            ProductImage(
                id = it.id,
                name = it.name
            )
        },
        inventory = if (entity.inventory != null)
            transformInventoryEntityToDomain(entity.inventory!!) else null,
        createdAt = entity.createdAt,
        updatedAt = entity.updatedAt
    )

    private fun transformInventoryEntityToDomain(
        entity: InventoryEntity
    ): Inventory = Inventory(
        inventoryId = InventoryId(entity.id),
        amount = entity.amount
    )


    private fun transformInventoryDomainToEntity(
        domain: Inventory
    ): InventoryEntity = InventoryEntity(
        id = domain.id!!.value,
        amount = domain.amount
    )
}