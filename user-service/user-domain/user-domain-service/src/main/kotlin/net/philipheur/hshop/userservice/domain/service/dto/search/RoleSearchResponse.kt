package net.philipheur.hshop.userservice.domain.service.dto.search

import net.philipheur.hshop.userservice.domain.service.dto.RoleDto

data class RoleSearchResponse(
    val total: Int,
    val roles: List<RoleDto>
)