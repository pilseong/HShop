package net.philipheur.hshop.catalogservice.dataaccess.catalog.adapter

import net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper.InventoryDataaccessMapper
import net.philipheur.hshop.catalogservice.dataaccess.catalog.repository.InventoryJpaRepository
import net.philipheur.hshop.catalogservice.domain.core.entity.Inventory
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.InventoryRepository
import org.springframework.stereotype.Component

@Component
class InventoryRepositoryAdapter(
    val repository: InventoryJpaRepository,
    private val inventoryDataaccessMapper: InventoryDataaccessMapper
): InventoryRepository {
    override fun createInventory(inventory: Inventory) {
        repository.save(
            inventoryDataaccessMapper.transformInventoryDomainToEntity(inventory)
        )
    }
}