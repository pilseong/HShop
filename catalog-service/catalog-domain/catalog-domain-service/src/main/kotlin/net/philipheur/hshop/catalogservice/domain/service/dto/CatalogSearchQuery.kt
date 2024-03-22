package net.philipheur.hshop.catalogservice.domain.service.dto

import jakarta.validation.constraints.NotEmpty


data class CatalogSearchQuery(
    @field:NotEmpty
    val keyword: String
)