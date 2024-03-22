package net.philipheur.hshop.userservice.domain.service.dto.auth

import jakarta.validation.constraints.NotNull
import java.util.UUID

data class UserCreateResponse(
    @NotNull val id: UUID,
    @NotNull val message: String
)