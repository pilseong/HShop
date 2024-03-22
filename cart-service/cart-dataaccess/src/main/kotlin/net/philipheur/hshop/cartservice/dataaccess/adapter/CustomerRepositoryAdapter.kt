package net.philipheur.hshop.cartservice.dataaccess.adapter

import net.philipheur.hshop.cartservice.dataaccess.entity.CartEntity
import net.philipheur.hshop.cartservice.dataaccess.entity.CartItemEntity
import net.philipheur.hshop.cartservice.dataaccess.entity.CustomerEntity
import net.philipheur.hshop.cartservice.dataaccess.repository.CartJpaRepository
import net.philipheur.hshop.cartservice.dataaccess.repository.CustomerJpaRepository
import net.philipheur.hshop.cartservice.domain.core.entity.Cart
import net.philipheur.hshop.cartservice.domain.core.entity.CartItem
import net.philipheur.hshop.cartservice.domain.core.entity.Customer
import net.philipheur.hshop.cartservice.domain.service.ports.output.repository.CustomerRepository
import net.philipheur.hshop.common.domain.valueobject.CartId
import net.philipheur.hshop.common.domain.valueobject.CustomerId
import net.philipheur.hshop.common.domain.valueobject.Money
import net.philipheur.hshop.common.domain.valueobject.ProductId
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
open class CustomerRepositoryAdapter(
    private val customerJpaRepository: CustomerJpaRepository,
    private val cartRepository: CartJpaRepository,
) : CustomerRepository {

    override fun findCustomer(customerId: UUID): Customer? {
        val result = customerJpaRepository.findById(customerId)

        return if (result.isPresent) {
            val customer = result.get()
            Customer(
                customerId = CustomerId(customer.id),
                email = customer.email,
                firstName = customer.firstName,
                lastName = customer.lastName,
                cart = Cart(
                    cartId = CartId(customer.cart!!.id),
                    totalPrice = Money(customer.cart!!.totalPrice),
                    cartItems = customer.cart!!.cartItems.map { item ->
                        CartItem(
                            id = item.id,
                            productId = ProductId(item.productId),
                            productName = item.productName,
                            quantity = item.quantity,
                            unitPrice = Money(item.unitPrice),
                            totalPrice = Money(item.totalPrice),
                            status = item.status,
                            cart = null
                        )
                    }.toList()
                )
            )
        } else null
    }

    @Transactional
    override fun save(customer: Customer): Customer {
        var customerEntity = CustomerEntity(
            id = customer.id!!.value,
            email = customer.email,
            firstName = customer.firstName,
            lastName = customer.lastName
        )

//        val cartOption = cartRepository.findById(customer.cart.id!!.value)
//        val cartEntity = cartOption.get()
//        cartEntity.customer = customerEntity
//        cartRepository.save(cartEntity)

        val cartEntity = CartEntity(
            id = customer.cart.id!!.value,
            totalPrice = customer.cart.totalPrice!!.amount,
            customer = customerEntity,
            cartItems = customer.cart.cartItems.map {
                CartItemEntity(
                    id = it.id,
                    productId = it.productId.value,
                    productName = it.productName,
                    quantity = it.quantity,
                    unitPrice = it.unitPrice.amount,
                    totalPrice = it.totalPrice.amount,
                    status = it.status,
                )
            }.toSet(),
        )
        customerEntity.cart = cartEntity

        customerEntity = customerJpaRepository.save(customerEntity)

        return Customer(
            customerId = CustomerId(customerEntity.id),
            email = customerEntity.email,
            firstName = customerEntity.firstName,
            lastName = customerEntity.lastName,
            cart = Cart(
                cartId = CartId(customerEntity.cart!!.id),
                totalPrice = Money(customerEntity.cart!!.totalPrice),
                cartItems = customerEntity.cart!!.cartItems.map {
                    CartItem(
                        id = it.id,
                        productId = ProductId(it.productId),
                        productName = it.productName,
                        quantity = it.quantity,
                        unitPrice = Money(it.unitPrice),
                        totalPrice = Money(it.totalPrice),
                        status = it.status,
                        cart = null
                    )
                }.toList()
            )
        )
    }
}