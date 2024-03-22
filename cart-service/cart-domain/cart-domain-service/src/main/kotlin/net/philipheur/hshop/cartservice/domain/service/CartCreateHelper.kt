package net.philipheur.hshop.cartservice.domain.service

import net.philipheur.hshop.cartservice.domain.core.CartDomainService
import net.philipheur.hshop.cartservice.domain.core.entity.Cart
import net.philipheur.hshop.cartservice.domain.service.dto.search.SearchCartCommand
import net.philipheur.hshop.cartservice.domain.service.ports.output.repository.CartRepository
import net.philipheur.hshop.common.domain.valueobject.CartId
import net.philipheur.hshop.common.domain.valueobject.CustomerId
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Component
open class CartCreateHelper(
    private val cartRepository: CartRepository,
    private val cartDomainService: CartDomainService,
    private val passwordEncoder: PasswordEncoder
) {
    private val log by LoggerDelegator()
    @Transactional
    open fun fetchCart(
        command: SearchCartCommand
    ) {
        // 고객 정보 확인 - 고객이 있는 경우에만 등록 가능

        // 카트 검색

        // 카트 정보를 못찾았을 경우
        // 카트 객체 생성
//        cartDomainService.validateAndInitiateCart()

        // 카트가 있을 경우 카트 정보를 반환

//        cartRepository.save(customer)
//
//        log.info(
//            "Returning CreateOrderResponseDto with " +
//                    "order id: ${event.customer.id!!.value}"
//        )

//        return event
//        return CustomerCreatedEvent(
//            Customer(),
//            ZonedDateTime.now()
//        )
    }
}