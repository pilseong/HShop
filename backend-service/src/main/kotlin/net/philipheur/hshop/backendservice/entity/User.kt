package net.philipheur.hshop.backendservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(length = 128, nullable = false, unique = true)
    var email: String,

    @Column(length = 64, nullable = false)
    var password: String,

    @Column(name = "first_name", length = 45, nullable = false)
    var firstName: String,

    @Column(name = "last_name", length = 45, nullable = false)
    var lastName: String,

    @Column(length = 128)
    var photos: String? = null,

    var enabled: Boolean = false,

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
        ]
    )
    // JoinColumn 의 name 은 중간 테이블의 컬럼명
    // referenceName 은 각 참조 테이블의 주키가 된다.
    @JoinTable(
        name = "users_roles",
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
    var roles: MutableSet<Role> = mutableSetOf()
) {
    override fun toString(): String {
        return "User(id=$id, email='$email', password='$password', firstName='$firstName', lastName='$lastName', photos=$photos, enabled=$enabled, roles=$roles)"
    }
}