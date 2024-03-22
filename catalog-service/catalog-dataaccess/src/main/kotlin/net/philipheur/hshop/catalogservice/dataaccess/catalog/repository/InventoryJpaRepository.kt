package net.philipheur.hshop.catalogservice.dataaccess.catalog.repository

import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.InventoryEntity
import org.springframework.data.jpa.repository.JpaRepository

import java.util.UUID

interface InventoryJpaRepository: JpaRepository<InventoryEntity, UUID> {
}