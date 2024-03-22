package net.philipheur.hshop.customerservice.dataaccess.repository

import net.philipheur.hshop.customerservice.dataaccess.entity.CustomerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface CustomerJpaRepository: JpaRepository<CustomerEntity, UUID> {
    fun findByEmail(email: String): CustomerEntity?

    @Query(
        value = "SELECT c " +
                "FROM   CustomerEntity c " +
                "WHERE  CONCAT(c.id, ' ', c.email, ' ', c.firstName, ' ', c.lastName) LIKE %:keyword% "
    )
    fun findAllWithKeyword(keyword: String, pageable: Pageable): Page<CustomerEntity>
}