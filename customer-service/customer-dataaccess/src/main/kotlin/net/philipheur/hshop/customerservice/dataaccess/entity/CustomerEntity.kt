package net.philipheur.hshop.customerservice.dataaccess.entity

import jakarta.persistence.*
import net.philipheur.hshop.common.domain.valueobject.CustomerStatus
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "customers", schema = "customer")
class CustomerEntity(
    @Id
    var id: UUID,

    @Column(nullable = false, length = 64)
    var email: String,
    @Column(nullable = false, length = 64)
    var password: String,
    @Column(nullable = true, length = 256)
    var verificationCode: String? = null,
    @Column(nullable = false, length = 64)
    var firstName: String,
    @Column(nullable = true, length = 64)
    var lastName: String? = null,

    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,
    @Column(nullable = true, length = 16)
    var phoneNumber: String? = null,

    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.EAGER
    )
    var customerAddresses: Set<CustomerAddressEntity>,

    @CreationTimestamp
    var createdAt: ZonedDateTime? = null,
)