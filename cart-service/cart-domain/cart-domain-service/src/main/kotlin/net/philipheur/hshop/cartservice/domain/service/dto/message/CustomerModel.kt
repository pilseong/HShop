package net.philipheur.hshop.cartservice.domain.service.dto.message
import java.util.UUID

data class CustomerModel(
    val id: UUID,
    val email: String,
    val firstName: String,
    val lastName: String
)