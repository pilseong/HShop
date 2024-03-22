package net.philipheur.hshop.catalogservice.domain.service.dto

import net.philipheur.hshop.common.domain.valueobject.ProductStatus
import java.math.BigDecimal
import java.util.UUID

data class CreateProductCommand(
    val name: String,
    val alias: String,
    val brandId: UUID,
    val categoryId: UUID,
    val status: ProductStatus,
    val cost: BigDecimal,
    val price: BigDecimal,
    val discountPercent: BigDecimal,
    val stock: Long
)