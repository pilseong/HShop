package net.philipheur.hshop.common.domain.dtos.settings

import java.util.UUID

data class StateDto(
    val id: UUID?,
    val name: String,
    val country: CountryDto?
)