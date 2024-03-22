package net.philipheur.hshop.userservice.domain.service.mapper

import net.philipheur.hshop.userservice.domain.core.entity.Role
import net.philipheur.hshop.userservice.domain.core.entity.User
import net.philipheur.hshop.userservice.domain.service.dto.RoleDto
import net.philipheur.hshop.userservice.domain.service.dto.UserDto
import org.springframework.stereotype.Component

@Component
class UserDomainServiceMapper {

    fun transformUserDomainToDto(user: User): UserDto {
        return UserDto(
            id = user.id!!.value,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            photo = user.photo ?: "",
            status = user.status.name,
            roles = user.roles.map { transformRoleDomainToDto(it) }
        )
    }

    fun transformRoleDomainToDto(role: Role): RoleDto {
        return RoleDto(
            id = role.id!!.value,
            name = role.name,
            description = role.description
        )
    }

}