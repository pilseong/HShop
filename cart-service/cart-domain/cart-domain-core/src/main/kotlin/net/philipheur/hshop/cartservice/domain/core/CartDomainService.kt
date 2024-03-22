package net.philipheur.hshop.cartservice.domain.core

import net.philipheur.hshop.cartservice.domain.core.entity.Cart

interface CartDomainService {
    fun validateAndInitiateCart(cart: Cart)
}