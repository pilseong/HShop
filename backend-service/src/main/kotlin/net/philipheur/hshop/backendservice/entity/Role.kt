package net.philipheur.hshop.backendservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "roles")
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(length = 40, nullable = false, unique = true)
    var name: String,

    @Column(length = 150, nullable = false)
    var description: String,
) {
    override fun toString(): String {
        return name
    }
}