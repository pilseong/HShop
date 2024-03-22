package net.philipheur.hshop.settingsservice.domain.service.dto

import java.util.UUID

data class StateDto(
    val id: UUID?,
    val name: String,
    val country: CountryDto?
)