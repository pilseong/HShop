package net.philipheur.hshop.userservice.domain.service.dto.edit

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID

data class UserEditCommand(
    @field:NotNull
    val id: UUID,
    @field:Email @field:NotEmpty
    val email: String,
    @field:NotNull
//    @Size(min = 4, max = 32, message = "Password size should be within 4 to 32 characters")
    val password: String,
    @field:NotEmpty
    val firstName: String,
    @field:NotEmpty
    val lastName: String,
    val photo: String? = null,
    @field:NotNull
    val status: String,
    @field:NotNull
    val roles: List<UUID>,
)