package net.philipheur.hshop.userservice.dataaccess.role.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import java.util.UUID

@Entity
@Table(name = "roles", schema = "users")
class RoleEntity(
    @Id
    var id: UUID? = null,

    @Column(length = 40, nullable = false, unique = true)
    var name: String,

    @Column(length = 150, nullable = false)
    var description: String,

    @CreationTimestamp
    var createdAt: ZonedDateTime? = null
)