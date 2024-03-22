package net.philipheur.hshop.userservice.domain.service.dto.search

import jakarta.validation.constraints.NotNull
import java.util.UUID

class UserSearchCommand(
    @field:NotNull
    val userId: UUID,
    val keyword: String?
)