package net.philipheur.hshop.customerservice.dataaccess.entity

import jakarta.persistence.*

import java.util.UUID

@Entity
@Table(name = "countries", schema = "customer")
class CountryEntity(
    @Id
    var id: UUID,

    @Column(nullable = false)
    var countryId: UUID,

    @Column(nullable = false, length = 50)
    var name: String,

    @Column(nullable = false, length = 5)
    var code: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_address_id")
    var address: CustomerAddressEntity,
)