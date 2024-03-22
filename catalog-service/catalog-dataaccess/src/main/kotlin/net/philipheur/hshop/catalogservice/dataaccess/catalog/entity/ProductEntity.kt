package net.philipheur.hshop.catalogservice.dataaccess.catalog.entity

import jakarta.persistence.*
import net.philipheur.hshop.common.domain.valueobject.ProductStatus
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.ZonedDateTime

import java.util.UUID

@Entity
@Table(name = "products", schema = "catalog")
class ProductEntity(
    @Id
    var id: UUID,
    @Column(length = 256, nullable = false)
    var name: String,
    @Column(length = 256, nullable = false)
    var alias: String,
    @Column(length = 1024, nullable = false)
    var shortDescription: String,
    @Column(length = 4096, nullable = false)
    var fullDescription: String,

    @Enumerated(EnumType.STRING)
    var status: ProductStatus,

    @ManyToOne
    @JoinColumn(name = "brand_id")
    var brand: BrandEntity,

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: CategoryEntity,

    var mainImage: String,

    var discountPercent: BigDecimal,
    var price: BigDecimal,
    var cost: BigDecimal,

    var length: Double,
    var width: Double,
    var height: Double,
    var weight: Double,


    @OneToOne(
        cascade = [CascadeType.ALL],
        mappedBy = "product"
    )
    var inventory: InventoryEntity? = null,

    @OneToMany(
        mappedBy = "product",
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER,
        orphanRemoval = true
    )
    var details: MutableSet<ProductDetailEntity> = mutableSetOf(),

    @OneToMany(
        mappedBy = "product",
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER,
        orphanRemoval = true
    )
    var detailImages: MutableSet<ProductImageEntity> = mutableSetOf(),

    var createdAt: ZonedDateTime? = null,

    @field:UpdateTimestamp
    var updatedAt: ZonedDateTime? = null,
)