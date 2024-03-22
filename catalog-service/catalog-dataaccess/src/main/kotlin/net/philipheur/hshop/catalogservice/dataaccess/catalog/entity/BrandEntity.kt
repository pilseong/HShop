package net.philipheur.hshop.catalogservice.dataaccess.catalog.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import java.util.UUID

@Entity
@Table(name = "brands", schema = "catalog")
class BrandEntity(
    @Id
    var id: UUID,
    @Column(length = 50, nullable = false, unique = true)
    var name: String,
    @Column(length = 128, nullable = false, unique = true)
    var logo: String,

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
        name = "brands_categories",
        schema = "catalog",
        joinColumns = [
            JoinColumn(
                name = "brand_id",
                referencedColumnName = "id"
            )
        ],
        inverseJoinColumns = [
            JoinColumn(
                name = "category_id",
                referencedColumnName = "id"
            )
        ]
    )
    var categories: MutableSet<CategoryEntity> = mutableSetOf(),

    @field:CreationTimestamp
    var createdAt: ZonedDateTime? = null
)