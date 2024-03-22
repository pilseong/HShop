package net.philipheur.hshop.catalogservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.BaseEntity
import net.philipheur.hshop.common.domain.valueobject.InventoryId
import net.philipheur.hshop.common.domain.valueobject.ProductId

class Inventory(
    inventoryId: InventoryId,
    var product: Product? = null,
    var amount: Long

): BaseEntity<InventoryId>(inventoryId) {
    fun addInventory(amount: Long) {
        this.amount += amount
    }

    fun subtractInventory(amount: Long) {
        this.amount -= amount
    }
}