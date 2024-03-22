package net.philipheur.hshop.cartservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.AggregateRoot
import net.philipheur.hshop.common.domain.valueobject.CartId
import net.philipheur.hshop.common.domain.valueobject.CustomerId
import net.philipheur.hshop.common.domain.valueobject.CustomerStatus
import net.philipheur.hshop.common.domain.valueobject.Money
import java.time.ZonedDateTime

class Cart(
    cartId: CartId,
    var customer: Customer? = null,
    var cartItems: List<CartItem> = emptyList(),
    var totalPrice: Money? = null,
    var createdAt: ZonedDateTime? = null,
    var updatedAt: ZonedDateTime? = null
) : AggregateRoot<CartId>(cartId)