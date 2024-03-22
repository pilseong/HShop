package net.philipheur.hshop.userservice.domain.service

import net.philipheur.hshop.common.domain.valueobject.UserId
import net.philipheur.hshop.common.domain.valueobject.UserStatus
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.userservice.domain.core.entity.Role
import net.philipheur.hshop.userservice.domain.core.entity.User
import net.philipheur.hshop.userservice.domain.core.exception.UserDuplicatedException
import net.philipheur.hshop.userservice.domain.core.exception.UserNotFoundException
import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateCommand
import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateResponse
import net.philipheur.hshop.userservice.domain.service.dto.edit.UserEditCommand
import net.philipheur.hshop.userservice.domain.service.dto.edit.UserEditResponse
import net.philipheur.hshop.userservice.domain.service.dto.search.RoleSearchResponse
import net.philipheur.hshop.userservice.domain.service.dto.search.UserSearchResponse
import net.philipheur.hshop.userservice.domain.service.mapper.UserDomainServiceMapper
import net.philipheur.hshop.userservice.domain.service.ports.input.service.UserService
import net.philipheur.hshop.userservice.domain.service.ports.output.repository.RoleRepository
import net.philipheur.hshop.userservice.domain.service.ports.output.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path

import java.util.UUID

@Service
class UserServiceImpl(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
//    private val passwordEncoder: PasswordEncoder,
    private val mapper: UserDomainServiceMapper
) : UserService {

    private val log by LoggerDelegator()

    override fun editUser(
        command: UserEditCommand,
        image: MultipartFile?
    ): UserEditResponse {
        var user = userRepository.findById(command.id)
            ?: throw UserNotFoundException("cannot find user with id ${command.id}")

        val roles = mutableListOf<Role>()

        command.roles.forEach {
            val role = roleRepository.findById(it)
            if (role != null) roles.add(role)
        }

        // 사진이 변경 된 경우 기존 사진 삭제 후 저장한다.
        if (image != null) {
            if (!user.photo.isNullOrEmpty())
                userRepository.cleanPhotoFile(Path.of("photos/users/${command.id}"))

            val savedFilename = userRepository.savePhotoFile(
                path = Path.of("photos/users/${command.id}"),
                filename = command.photo!!,
                image = image
            )
            user.photo = savedFilename
        } else {
            if (command.photo.isNullOrEmpty() && !user.photo.isNullOrEmpty()) {
                userRepository.cleanPhotoFile(Path.of("photos/users/${command.id}"))
                user.photo = null
            }
        }

        // 유저저장
        user.firstName = command.firstName
        user.lastName = command.lastName

        if (command.password.isNotEmpty())
            user.password = BCryptPasswordEncoder().encode(command.password)

        user.status = UserStatus.valueOf(command.status)
        user.roles = roles

        user = userRepository.save(user)

        return UserEditResponse(
            id = user.id!!.value,
            message = "User Edited Successfully"
        )
    }

    // 유저리스트를 받는 서비스
    // 현재는 command 로 유저 id를 받지만
    // 추후  jwt, paseto 로 변경하여 credential 확인 방식
    override fun findAllUsers(
        keyword: String?,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String
    ): UserSearchResponse {
        val usersPageResponse = userRepository.findAll(
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
            keyword = keyword ?: ""
        )

        return UserSearchResponse(
            totalElements = usersPageResponse.totalElements,
            totalPages = usersPageResponse.totalPages,
            pageNo = usersPageResponse.pageNo,
            pageSize = usersPageResponse.pageSize,
            last = usersPageResponse.last,
            users = usersPageResponse.content.map {
                mapper.transformUserDomainToDto(it)
            }
        )
    }

    override fun findUserById(id: UUID): UserSearchResponse {
        val user = userRepository.findById(id)
            ?: throw UserNotFoundException("cannot find the user with id ${id}")

        return UserSearchResponse(
            totalElements = 1,
            totalPages = 1,
            pageNo = 1,
            pageSize = 1,
            last = true,
            users = listOf(mapper.transformUserDomainToDto(user))
        )
    }

    override fun findAllRoles(): RoleSearchResponse {
        val roles = roleRepository.findAll()

        val roleDtos = roles.map { mapper.transformRoleDomainToDto(it) }

        return RoleSearchResponse(
            total = roleDtos.size,
            roles = roleDtos
        )
    }

    override fun deleteUserById(userId: UUID) {
        userRepository.deleteById(userId)
    }

    /**
     * private method 구역
     */

    private fun isDuplicationEmail(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }

}