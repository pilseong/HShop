package net.philipheur.hshop.userservice.domain.service.dto.search

import net.philipheur.hshop.userservice.domain.service.dto.UserDto

data class UserSearchResponse(
    val totalElements: Long,
    val totalPages: Int,
    val pageNo: Int,
    val pageSize: Int,
    val last: Boolean,
    val users: List<UserDto>
)