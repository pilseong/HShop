package net.philipheur.hshop.catalogservice.dataaccess.catalog.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

import java.util.UUID

@Entity
@Table(name = "inventory", schema = "catalog")
class InventoryEntity(
    @Id
    val id: UUID,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "productId", referencedColumnName = "id")
    var product: ProductEntity? = null,

    @Column(nullable = false)
    val amount: Long
)