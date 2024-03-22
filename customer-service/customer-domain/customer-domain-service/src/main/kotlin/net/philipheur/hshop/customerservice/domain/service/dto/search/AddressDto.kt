package net.philipheur.hshop.customerservice.domain.service.dto.search

import net.philipheur.hshop.common.domain.dtos.settings.CountryDto
import net.philipheur.hshop.common.domain.dtos.settings.StateDto
import java.util.UUID

data class AddressDto(
    val id: UUID,
    val address1: String,
    val address2: String,
    val city: String,
    val state: StateDto,
    val country: CountryDto,
    val postalCode: String
)