package net.philipheur.hshop.cartservice.domain.core.entity

import net.philipheur.hshop.common.domain.valueobject.CustomerId
import net.philipheur.hshop.common.domain.valueobject.Money
import net.philipheur.hshop.common.domain.valueobject.ProductId
import net.philipheur.hshop.common.domain.valueobject.ProductStatus
import java.util.UUID

class CartItem(
    var id: UUID,
    var productId: ProductId,
    var productName: String,
    var quantity: Long,
    var unitPrice: Money,
    var totalPrice: Money,

    var status: ProductStatus,
    var cart: Cart?
)