package net.philipheur.hshop.userservice.domain.service.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

import java.util.UUID

data class RoleDto(
    @NotNull
    val id: UUID,
    @NotEmpty
    val name: String,
    @NotEmpty
    val description: String
)