package net.philipheur.hshop.userservice.domain.service.ports.input.service

import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateCommand
import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateResponse
import net.philipheur.hshop.userservice.domain.service.dto.edit.UserEditCommand
import net.philipheur.hshop.userservice.domain.service.dto.edit.UserEditResponse
import net.philipheur.hshop.userservice.domain.service.dto.search.RoleSearchResponse
import net.philipheur.hshop.userservice.domain.service.dto.search.UserSearchResponse
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

interface UserService {
    fun editUser(
        command: UserEditCommand,
        image: MultipartFile?)
    : UserEditResponse

    fun findAllUsers(
        keyword: String?,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String
    ): UserSearchResponse

    fun findUserById(
        id: UUID
    ): UserSearchResponse

    fun findAllRoles(): RoleSearchResponse

    fun deleteUserById(userId: UUID)

}