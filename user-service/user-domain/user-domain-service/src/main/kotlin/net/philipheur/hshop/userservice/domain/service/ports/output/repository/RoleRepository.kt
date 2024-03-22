package net.philipheur.hshop.userservice.domain.service.ports.output.repository

import net.philipheur.hshop.userservice.domain.core.entity.Role
import java.util.UUID

interface RoleRepository {
    fun findByName(name: String): Role?
    fun findById(id: UUID): Role?
    fun findAll(): List<Role>
    fun save(role: Role): Role
}