package net.philipheur.hshop.customerservice.dataaccess.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "states", schema = "customer")
class StateEntity (
    @Id
    var id: UUID,

    @Column(nullable = false)
    var stateId: UUID,

    @Column(nullable = false, length = 60)
    var name: String,

    @OneToOne
    @JoinColumn(name = "customer_address_id")
    var address: CustomerAddressEntity
)