package net.philipheur.hshop.userservice.domain.service.dto.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID

data class UserCreateCommand(
    @field:Email
    @field:NotEmpty(message = "Email cannot be empty")
    val email: String,
    @field:NotEmpty
    @field:Size(min = 4, max = 32, message = "Password size should be within 4 to 32 characters")
    val password: String,
    @field:NotEmpty
    @field:Size(min = 2, max = 32, message = "First name should be within 2 to 32 characters")
    val firstName: String,
    @field:NotEmpty
    @field:Size(min = 2, max = 32, message = "Last name should be within 2 to 32 characters")
    val lastName: String,
    val photo: String? = null,
    @field:NotNull
    val status: String,
    @field:NotNull
    val roles: List<UUID>,
)