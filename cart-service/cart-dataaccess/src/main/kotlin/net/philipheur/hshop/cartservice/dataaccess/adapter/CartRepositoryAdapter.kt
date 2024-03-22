package net.philipheur.hshop.cartservice.dataaccess.adapter

import net.philipheur.hshop.cartservice.dataaccess.entity.CartEntity
import net.philipheur.hshop.cartservice.dataaccess.entity.CartItemEntity
import net.philipheur.hshop.cartservice.dataaccess.entity.CustomerEntity
import net.philipheur.hshop.cartservice.dataaccess.mapper.CartDataAccessMapper
import net.philipheur.hshop.cartservice.dataaccess.repository.CartJpaRepository
import net.philipheur.hshop.cartservice.domain.core.entity.Cart
import net.philipheur.hshop.cartservice.domain.core.entity.CartItem
import net.philipheur.hshop.cartservice.domain.core.entity.Customer
import net.philipheur.hshop.cartservice.domain.service.ports.output.repository.CartRepository
import net.philipheur.hshop.common.domain.valueobject.CartId
import net.philipheur.hshop.common.domain.valueobject.CustomerId
import net.philipheur.hshop.common.domain.valueobject.Money
import net.philipheur.hshop.common.domain.valueobject.ProductId
import org.springframework.stereotype.Component

import java.util.UUID

@Component
class CartRepositoryAdapter(
    private val cartJpaRepository: CartJpaRepository,
    private val mapper: CartDataAccessMapper
) : CartRepository {
    override fun findCartByCustomerId(customerId: CustomerId): Cart {
        return Cart(cartId = CartId(UUID.randomUUID()))
    }

    override fun save(cart: Cart): Cart {
        val cartEntity = cartJpaRepository.save(
            CartEntity(
                id = cart.id!!.value,
                totalPrice = cart.totalPrice!!.amount,
                cartItems = cart.cartItems.map {
                    CartItemEntity(
                        id = it.id,
                        productId = it.productId.value,
                        productName = it.productName,
                        unitPrice = it.unitPrice.amount,
                        quantity = it.quantity,
                        totalPrice = it.totalPrice.amount,
                        status = it.status
                    )
                }.toSet()
            )
        )

        return Cart(
            cartId = CartId(cartEntity.id),
            cartItems = cartEntity.cartItems.map {
                CartItem(
                    id = it.id,
                    quantity = it.quantity,
                    unitPrice = Money(it.unitPrice),
                    status = it.status,
                    productId = ProductId(it.productId),
                    productName = it.productName,
                    totalPrice = Money(it.totalPrice),
                    cart = null
                )
            },
            totalPrice = Money(cartEntity.totalPrice),
            createdAt = cartEntity.createdAt,
            updatedAt = cartEntity.updatedAt
        )
    }
}