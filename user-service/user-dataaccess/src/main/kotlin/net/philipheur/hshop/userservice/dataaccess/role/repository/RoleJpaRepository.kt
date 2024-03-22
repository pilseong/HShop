package net.philipheur.hshop.userservice.dataaccess.user.repository

import net.philipheur.hshop.userservice.dataaccess.role.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RoleJpaRepository: JpaRepository<RoleEntity, UUID> {
    fun findByName(name: String): RoleEntity?
    fun save(entity: RoleEntity): RoleEntity
}