package net.philipheur.hshop.customerservice.dataaccess.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

import java.util.UUID

@Entity
@Table(name = "customer_addresses", schema = "customer")
class CustomerAddressEntity(
    @Id
    var id: UUID,
    @Column(nullable = false, length = 120)
    var address1: String,
    @Column(nullable = true, length = 120)
    var address2: String,
    @Column(nullable = false, length = 40)
    var city: String,

    @OneToOne(
        mappedBy = "address",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var state: StateEntity,

    @OneToOne(
        mappedBy = "address",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var country: CountryEntity,

    @Column(nullable = false, length = 10)
    var postalCode: String,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerEntity
)
