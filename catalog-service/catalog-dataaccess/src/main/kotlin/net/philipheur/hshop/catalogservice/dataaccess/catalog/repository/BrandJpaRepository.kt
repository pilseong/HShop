package net.philipheur.hshop.catalogservice.dataaccess.catalog.repository

import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.BrandEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface BrandJpaRepository: JpaRepository<BrandEntity, UUID> {
    @Query(
        value = "SELECT b " +
                "FROM   BrandEntity b " +
                "WHERE  CONCAT(b.id, ' ', b.name) LIKE %:keyword% "
    )
    fun findAllWithKeyword(keyword: String, pageable: Pageable): Page<BrandEntity>
}