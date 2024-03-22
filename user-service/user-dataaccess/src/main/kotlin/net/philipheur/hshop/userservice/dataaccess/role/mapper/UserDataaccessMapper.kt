package net.philipheur.hshop.userservice.dataaccess.role.mapper

import net.philipheur.hshop.common.domain.valueobject.RoleId
import net.philipheur.hshop.common.domain.valueobject.UserId
import net.philipheur.hshop.userservice.dataaccess.role.entity.RoleEntity
import net.philipheur.hshop.userservice.dataaccess.user.entity.UserEntity
import net.philipheur.hshop.userservice.domain.core.entity.Role
import net.philipheur.hshop.userservice.domain.core.entity.User
import org.springframework.stereotype.Component

@Component
class UserDataaccessMapper {
    fun transformUserEntityToDomain(entity: UserEntity) = User(
        id = UserId(entity.id!!),
        email = entity.email,
        firstName = entity.firstName,
        lastName = entity.lastName,
        status = entity.status,
        photo = entity.photo,
        roles = entity.roles.map {
            Role(
                id = RoleId(it.id!!),
                name = it.name,
                description = it.description,
                createdAt = it.createdAt
            )
        }.toMutableList(),
        password = entity.password,
        createdAt = entity.createdAt
    )

    fun transformRoleDomainToEntity(role: Role) = RoleEntity(
        id = role.id?.value,
        name = role.name,
        description = role.description,
        createdAt = role.createdAt
    )

    fun transformRoleEntityToDomain(entity: RoleEntity): Role {
        return Role(
            id = RoleId(entity.id!!),
            name = entity.name,
            description = entity.description,
            createdAt = entity.createdAt
        )
    }

    fun transformUserDomainToEntity(user: User) = UserEntity(
        id = user.id?.value,
        password = user.password,
        firstName = user.firstName,
        lastName = user.lastName,
        status = user.status,
        photo = user.photo,
        roles = user.roles.map {
            RoleEntity(
                id = it.id!!.value,
                name = it.name,
                description = it.description,
                createdAt = it.createdAt
            )
        }.toMutableSet(),
        email = user.email,
        createdAt = user.createdAt
    )
}