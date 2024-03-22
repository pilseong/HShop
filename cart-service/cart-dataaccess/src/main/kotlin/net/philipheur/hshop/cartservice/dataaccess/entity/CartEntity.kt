package net.philipheur.hshop.cartservice.dataaccess.entity

import jakarta.persistence.*
import net.philipheur.hshop.common.domain.valueobject.CustomerStatus
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "carts", schema = "cart")
class CartEntity(
    @Id
    var id: UUID,

    @OneToOne(
         cascade = [CascadeType.ALL]
    )
    @JoinColumn(
        name = "customer_id",
        referencedColumnName = "id"
    )
    var customer: CustomerEntity? = null,

    @OneToMany(
        mappedBy = "cart",
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    var cartItems: Set<CartItemEntity>,

    var totalPrice: BigDecimal,

    @field:UpdateTimestamp
    var updatedAt: ZonedDateTime? = null,

) {
    @field:CreationTimestamp
    lateinit var createdAt: ZonedDateTime
}