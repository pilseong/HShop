package net.philipheur.hshop.catalogservice.dataaccess.catalog.entity

import jakarta.persistence.*

@IdClass(ProductImageEntityId::class)
@Entity
@Table(name = "product_images", schema = "catalog")
class ProductImageEntity(
    @Id
    var id: Long,

    @Column(nullable = false)
    var name: String,

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: ProductEntity
)