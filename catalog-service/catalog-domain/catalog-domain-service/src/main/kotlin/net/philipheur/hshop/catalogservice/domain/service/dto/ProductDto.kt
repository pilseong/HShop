package net.philipheur.hshop.catalogservice.domain.service.dto

import net.philipheur.hshop.catalogservice.domain.core.valueobject.ProductDetail
import net.philipheur.hshop.catalogservice.domain.core.valueobject.ProductImage
import net.philipheur.hshop.common.domain.valueobject.Dimension
import net.philipheur.hshop.common.domain.valueobject.ProductStatus
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

data class ProductDto(
    val id: UUID,
    val name: String,
    val shortName: String,
    val alias: String,
    val shortDescription: String,
    val fullDescription: String,
    val status: ProductStatus,
    val mainImage: String,
    val discountPercent: BigDecimal,
    val price: BigDecimal,
    val cost: BigDecimal,
    val dimension: Dimension,
    val brand: BrandDto,
    val category: CategoryDto,
    val details: List<ProductDetail>,
    val detailImages: List<ProductImage>,
    val displayPrice: String,
    val discountPrice: String,
    val inventory: InventoryDto,
    val createdAt: ZonedDateTime? = null,
    val updatedAt: ZonedDateTime? = null
)