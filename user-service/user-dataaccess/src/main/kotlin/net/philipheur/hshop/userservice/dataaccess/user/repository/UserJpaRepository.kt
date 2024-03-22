package net.philipheur.hshop.userservice.dataaccess.user.repository

import net.philipheur.hshop.userservice.dataaccess.user.entity.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface UserJpaRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?

    @Query(
        value = "SELECT u " +
                "FROM   UserEntity u " +
                "WHERE  CONCAT(u.id, ' ', u.email, ' ', u.firstName, ' ', u.lastName) LIKE %:keyword% "
    )
    fun findAllWithKeyword(keyword: String, pageable: Pageable): Page<UserEntity>
}