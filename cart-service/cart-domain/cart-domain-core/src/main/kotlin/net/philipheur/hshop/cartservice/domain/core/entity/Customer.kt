package net.philipheur.hshop.cartservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.AggregateRoot
import net.philipheur.hshop.common.domain.valueobject.CustomerId

class Customer(
    customerId: CustomerId,
    var email: String,
    var firstName: String,
    var lastName: String?,
    var cart: Cart
): AggregateRoot<CustomerId>(customerId)