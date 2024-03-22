package net.philipheur.hshop.catalogservice.domain.service.dto

import java.util.UUID

data class InventoryDto(
    val id: UUID,
    val productId: UUID,
    val amount: Long
) {
}