package net.philipheur.hshop.userservice.dataaccess.role.adapter

import net.philipheur.hshop.userservice.dataaccess.role.mapper.UserDataaccessMapper
import net.philipheur.hshop.userservice.dataaccess.user.repository.RoleJpaRepository
import net.philipheur.hshop.userservice.domain.core.entity.Role
import net.philipheur.hshop.userservice.domain.core.exception.UserDomainException
import net.philipheur.hshop.userservice.domain.service.ports.output.repository.RoleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleRepositoryAdapter(
    private val repository: RoleJpaRepository,
    private val mapper: UserDataaccessMapper
) : RoleRepository {
    override fun findByName(name: String): Role? {
        val role = repository.findByName(name)

        if (role != null) {
            return mapper.transformRoleEntityToDomain(role)
        }

        return null
    }

    override fun findById(id: UUID): Role? {
        val role = repository.findById(id)
        if (role.isPresent) {
            return mapper.transformRoleEntityToDomain(role.get())
        }

        throw UserDomainException("invalid role with id $id")
    }

    override fun findAll(): List<Role> {
        val roles = repository.findAll()
        return roles.map { mapper.transformRoleEntityToDomain(it) }
    }

    override fun save(role: Role): Role {
        val entity = repository.save(mapper.transformRoleDomainToEntity(role))

        return mapper.transformRoleEntityToDomain(entity)
    }
}