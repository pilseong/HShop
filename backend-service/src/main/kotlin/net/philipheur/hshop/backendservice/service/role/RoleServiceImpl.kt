package net.philipheur.hshop.backendservice.service.role

import net.philipheur.hshop.backendservice.dto.role.RoleDto
import net.philipheur.hshop.backendservice.entity.Role
import net.philipheur.hshop.backendservice.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(
    private val roleRepository: RoleRepository
): RoleService {
    override fun findAll(): List<RoleDto> {
        return roleRepository.findAll().map {
            RoleDto(
                id = it.id,
                name = it.name,
                description = it.description
            )
        }.toMutableList()
    }
}