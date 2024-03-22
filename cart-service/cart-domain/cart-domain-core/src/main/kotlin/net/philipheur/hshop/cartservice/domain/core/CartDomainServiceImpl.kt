package net.philipheur.hshop.cartservice.domain.core

import net.philipheur.hshop.cartservice.domain.core.entity.Cart
import net.philipheur.hshop.common.domain.valueobject.CustomerStatus
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import java.time.ZonedDateTime

class CartDomainServiceImpl : CartDomainService {

    private val log by LoggerDelegator()

    // 고객 생성 이벤트를 생성하고 돌려 준다. 로직이 없다.
    override fun validateAndInitiateCart(cart: Cart) {
        log.info("Cart for Customer with id: ${cart.id!!.value} is initiated")
    }
}