package net.philipheur.hshop.cartservice.domain.service.ports.output.repository

import net.philipheur.hshop.cartservice.domain.core.entity.Cart
import net.philipheur.hshop.common.domain.valueobject.CustomerId

interface CartRepository {
    fun findCartByCustomerId(customerId: CustomerId): Cart
    fun save(cart: Cart): Cart
}