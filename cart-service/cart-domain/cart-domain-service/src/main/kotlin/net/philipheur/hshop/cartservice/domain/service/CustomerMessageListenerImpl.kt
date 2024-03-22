package net.philipheur.hshop.cartservice.domain.service


import net.philipheur.hshop.cartservice.domain.core.entity.Cart
import net.philipheur.hshop.cartservice.domain.core.entity.Customer
import net.philipheur.hshop.cartservice.domain.core.exception.CartDomainException
import net.philipheur.hshop.cartservice.domain.service.dto.message.CustomerModel
import net.philipheur.hshop.cartservice.domain.service.ports.input.message.CustomerMessageListener
import net.philipheur.hshop.cartservice.domain.service.ports.output.repository.CartRepository
import net.philipheur.hshop.cartservice.domain.service.ports.output.repository.CustomerRepository
import net.philipheur.hshop.common.domain.valueobject.CartId
import net.philipheur.hshop.common.domain.valueobject.CustomerId
import net.philipheur.hshop.common.domain.valueobject.Money
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.*

@Service
class CustomerMessageListenerImpl(
    private val customerRepository: CustomerRepository,
    private val cartRepository: CartRepository,
) : CustomerMessageListener {

    private val log by LoggerDelegator()

    override fun customerCreated(customerModel: CustomerModel) {
        // 고객 id 조회
        var customer = customerRepository.findCustomer(customerModel.id)

        // 있을 경우는 무시한다.
        if (customer != null) {
            throw CartDomainException("customer with id ${customerModel.id} already exists")
        }

        // 고객이 없을 경우 생성
        log.info("Customer is created in database with id: ${customerModel.id}")

        // 빈카트 생성
        val cart = Cart(
            cartId = CartId(UUID.randomUUID()),
            cartItems = emptyList(),
            totalPrice = Money.ZERO,
        )

        // 고객 객체 생성
        customer = Customer(
            customerId = CustomerId(customerModel.id),
            email = customerModel.email,
            firstName = customerModel.firstName,
            lastName = customerModel.lastName,
            cart = cart
        )

        cart.customer = customer

        customerRepository.save(customer)

        log.info("Empty cart for customer with id $customerModel.id} is created")
    }
}