package net.philipheur.hshop.userservice.domain.service.dto.auth

import jakarta.validation.constraints.NotEmpty

data class LoginQuery(
    @field:NotEmpty
    val email: String,
    @field:NotEmpty
    val password: String
)
