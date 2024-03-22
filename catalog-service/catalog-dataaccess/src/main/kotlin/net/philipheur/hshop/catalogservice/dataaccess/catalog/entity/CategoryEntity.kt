package net.philipheur.hshop.catalogservice.dataaccess.catalog.entity

import jakarta.persistence.*
import net.philipheur.hshop.common.domain.valueobject.CategoryStatus
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

import java.util.UUID

@Entity
@Table(name = "categories", schema = "catalog")
class CategoryEntity(
    @Id
    var id: UUID,

    @Column(length = 128, nullable = false, unique = true)
    var name: String,

    @Column(length = 64, nullable = false, unique = true)
    var alias: String,

    @Column(length = 128, nullable = false)
    var image: String,

    @Column(length = 128, nullable = false)
    @Enumerated(EnumType.STRING)
    var status: CategoryStatus,

    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parent: CategoryEntity?,

    @OneToMany(
        mappedBy = "parent",
        fetch = FetchType.EAGER,
        cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,
        ]
    )
    var subCategories: MutableSet<CategoryEntity>,

    @field:CreationTimestamp
    var createdAt: ZonedDateTime? = null
)