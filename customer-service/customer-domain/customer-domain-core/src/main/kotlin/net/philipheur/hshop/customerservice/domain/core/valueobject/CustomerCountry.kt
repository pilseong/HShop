package net.philipheur.hshop.customerservice.domain.core.valueobject

import java.util.UUID

data class CustomerCountry(
    val id: UUID,
    val countryId: UUID,
    val name: String,
    val code: String
)