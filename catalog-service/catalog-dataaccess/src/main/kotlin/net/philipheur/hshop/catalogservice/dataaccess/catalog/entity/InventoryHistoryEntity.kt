package net.philipheur.hshop.catalogservice.dataaccess.catalog.entity

import jakarta.persistence.*
import net.philipheur.hshop.catalogservice.domain.core.valueobject.TransactionType
import java.time.ZonedDateTime

import java.util.UUID

@Entity
@Table(name = "inventory_history", schema = "catalog")
class InventoryHistoryEntity(
    @Id
    var id: UUID,

    @Column(nullable = false)
    var productId: UUID,

    @Column(nullable = false)
    var operator: UUID,

    @Column(nullable = false)
    var amount: Long,

    @Enumerated(EnumType.STRING)
    val type: TransactionType,

    val createdAt: ZonedDateTime
) {
}