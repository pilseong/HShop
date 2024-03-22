package net.philipheur.hshop.userservice.domain.service.dto.edit

import jakarta.validation.constraints.NotNull
import java.util.UUID

data class UserEditResponse(
    @NotNull val id: UUID,
    @NotNull val message: String
)