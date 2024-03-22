package net.philipheur.hshop.common.domain.dtos.settings

import java.util.UUID

data class CountryDto(
    val id: UUID?,
    val name: String,
    val code: String,
    val states: List<StateDto>?
)