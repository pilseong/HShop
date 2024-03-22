package net.philipheur.hshop.catalogservice.dataaccess.catalog.entity

import jakarta.persistence.*

@IdClass(ProductDetailEntityId::class)
@Entity
@Table(name = "product_details", schema = "catalog")
class ProductDetailEntity(
    @Id
    var id: Long,

    @Column(nullable = false, length = 512)
    var name: String,

    @Column(nullable = false, length = 1024)
    var value: String,

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: ProductEntity
)