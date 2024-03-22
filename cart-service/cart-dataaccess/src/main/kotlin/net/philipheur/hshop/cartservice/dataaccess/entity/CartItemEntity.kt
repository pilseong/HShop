package net.philipheur.hshop.cartservice.dataaccess.entity

import jakarta.persistence.*
import net.philipheur.hshop.common.domain.valueobject.ProductStatus
import java.math.BigDecimal

import java.util.UUID

@Entity
@Table(name = "cart_items", schema = "cart")
class CartItemEntity(
    @Id
    var id: UUID,

    @Column(nullable = false)
    var productId: UUID,

    @Column(nullable = false, length = 128)
    var productName: String,
    var quantity: Long,
    var unitPrice: BigDecimal,
    var totalPrice: BigDecimal,

    @Enumerated(EnumType.STRING)
    var status: ProductStatus,

    @ManyToOne
    @JoinColumn(name = "cart_id")
    var cart: CartEntity? = null,
)