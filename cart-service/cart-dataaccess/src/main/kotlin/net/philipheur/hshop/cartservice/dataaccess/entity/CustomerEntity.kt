package net.philipheur.hshop.cartservice.dataaccess.entity

import jakarta.persistence.*
import net.philipheur.hshop.common.domain.valueobject.CustomerStatus
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "customers", schema = "cart")
class CustomerEntity(
    @Id
    var id: UUID,

    @Column(nullable = false, length = 64)
    var email: String,
    @Column(nullable = false, length = 64)
    var firstName: String,
    @Column(nullable = true, length = 64)
    var lastName: String? = null,

    @OneToOne(
        mappedBy = "customer",
        cascade = [CascadeType.ALL]
    )
    var cart: CartEntity? = null
)