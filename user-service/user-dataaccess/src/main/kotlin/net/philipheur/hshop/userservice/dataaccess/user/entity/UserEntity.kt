package net.philipheur.hshop.userservice.dataaccess.user.entity

import jakarta.persistence.*
import net.philipheur.hshop.common.domain.valueobject.UserStatus
import net.philipheur.hshop.userservice.dataaccess.role.entity.RoleEntity
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import java.util.UUID

@Entity
@Table(name = "users", schema = "users")
//@Table(name = "users")
class UserEntity(
    @Id
    var id: UUID? = null,

    @Column(length = 128, nullable = false, unique = true)
    var email: String,

    @Column(length = 64, nullable = false)
    var password: String,

    @Column(name = "first_name", length = 45, nullable = false)
    var firstName: String,

    @Column(name = "last_name", length = 45, nullable = false)
    var lastName: String,

    @Column(length = 128)
    var photo: String? = null,

    @Column(name = "status", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    var status: UserStatus,

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,
        ]
    )
    // JoinColumn 의 name 은 중간 테이블의 컬럼명
    // referenceName 은 각 참조 테이블의 주키가 된다.
    @JoinTable(
        name = "users_roles",
        schema = "users",
        joinColumns = [
            JoinColumn(
                name = "user_id",
                referencedColumnName = "id"
            )
        ],
        inverseJoinColumns = [
            JoinColumn(
                name = "role_id",
                referencedColumnName = "id"
            )
        ]
    )
    var roles: MutableSet<RoleEntity> = mutableSetOf(),

    @CreationTimestamp
    var createdAt: ZonedDateTime? = null
)