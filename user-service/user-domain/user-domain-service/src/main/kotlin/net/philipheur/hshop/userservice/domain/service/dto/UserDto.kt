package net.philipheur.hshop.userservice.domain.service.dto

import java.util.UUID

data class UserDto(
    val id: UUID,
    val email: String,
    val firstName: String,
    val lastName: String,
    val photo: String = "",
    val status: String,
    val roles: List<RoleDto>,
)