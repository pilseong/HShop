package net.philipheur.hshop.backendservice.service.role

import net.philipheur.hshop.backendservice.dto.role.RoleDto
import net.philipheur.hshop.backendservice.entity.Role


interface RoleService {
    fun findAll(): List<RoleDto>
}