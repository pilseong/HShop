package net.philipheur.hshop.catalogservice.domain.core.entity

import net.philipheur.hshop.catalogservice.domain.core.valueobject.ProductDetail
import net.philipheur.hshop.catalogservice.domain.core.valueobject.ProductImage
import net.philipheur.hshop.common.domain.entity.AggregateRoot
import net.philipheur.hshop.common.domain.valueobject.*
import java.math.BigDecimal
import java.time.ZonedDateTime

class Product(
    id: ProductId,
    var name: String,
    var alias: String,
    var shortDescription: String,
    var fullDescription: String,
    var status: ProductStatus,
    var mainImage: String,
    var discountPercent: BigDecimal,
    var price: Money,
    var cost: Money,
    var dimension: Dimension,
    var brand: Brand,
    var category: Category,
    var inventory: Inventory? = null,
    var details: List<ProductDetail> = emptyList(),
    var detailImages: List<ProductImage> = emptyList(),
    var createdAt: ZonedDateTime? = null,
    var updatedAt: ZonedDateTime? = null
) : AggregateRoot<ProductId>(id) {
    val discountPrice: Money
        get() = when {
            this.discountPercent > BigDecimal.ZERO ->
                Money(
                    this.price.amount
                        .multiply(
                            (BigDecimal.valueOf(100) - this.discountPercent)
                                .div(BigDecimal.valueOf(100))
                        )
                )
            else -> {
                this.price
            }
        }
    val shortName: String
        get() = if (this.name.length > 50)
            "${this.name.substring(0, 50)}.."
        else this.name
}