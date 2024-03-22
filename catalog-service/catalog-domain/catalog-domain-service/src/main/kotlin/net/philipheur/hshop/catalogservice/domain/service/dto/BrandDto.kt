package net.philipheur.hshop.catalogservice.domain.service.dto

import java.util.UUID

data class BrandDto(
    val id: UUID,
    val name: String,
    val logo: String,
    val categories: List<CategoryDto>
)