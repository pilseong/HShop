package net.philipheur.hshop.backendservice.repository

import net.philipheur.hshop.backendservice.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}