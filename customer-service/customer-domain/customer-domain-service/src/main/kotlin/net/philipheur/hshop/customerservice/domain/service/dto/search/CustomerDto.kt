package net.philipheur.hshop.customerservice.domain.service.dto.search

import java.util.UUID

data class CustomerDto(
    val id: UUID,
    val email: String,
    val firstName: String,
    val lastName: String?,
    val status: String,
    val phoneNumber: String?,
    val addresses: List<AddressDto>
) {
}