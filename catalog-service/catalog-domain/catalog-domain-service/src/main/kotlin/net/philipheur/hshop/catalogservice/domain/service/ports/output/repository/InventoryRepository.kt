package net.philipheur.hshop.catalogservice.domain.service.ports.output.repository

import net.philipheur.hshop.catalogservice.domain.core.entity.Inventory

interface InventoryRepository {
    fun createInventory(inventory: Inventory)
}