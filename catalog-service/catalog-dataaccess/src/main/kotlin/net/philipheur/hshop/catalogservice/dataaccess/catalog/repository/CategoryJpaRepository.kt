package net.philipheur.hshop.catalogservice.dataaccess.catalog.repository

import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.CategoryEntity
import net.philipheur.hshop.common.domain.valueobject.CategoryStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

import java.util.UUID

interface CategoryJpaRepository : JpaRepository<CategoryEntity, UUID> {
    @Query(
        value = "SELECT c " +
                "FROM   CategoryEntity c " +
                "WHERE  CONCAT(c.id, ' ', c.name, ' ', c.alias) LIKE %:keyword% "
    )
    fun findAllWithKeyword(
        keyword: String,
        pageable: Pageable
    ): Page<CategoryEntity>

    @Query(
        value = "SELECT c " +
                "FROM   CategoryEntity c " +
                "WHERE  CONCAT(c.id, ' ', c.name, ' ', c.alias) LIKE %:keyword% " +
                "   AND status = :enabled"
    )
    fun findAllWithKeywordAndStatus(
        keyword: String,
        enabled: CategoryStatus,
        pageable: Pageable
    ): Page<CategoryEntity>


    @Query(
        value = "SELECT c " +
                "FROM CategoryEntity c " +
                "WHERE c.id NOT IN (" +
                "SELECT DISTINCT cs.parent.id " +
                "FROM CategoryEntity cs " +
                "WHERE cs.parent.id IS NOT null) " +
                "   AND CONCAT(c.id, ' ', c.name, ' ', c.alias) LIKE %:keyword% "
    )
    fun findAllWithKeywordOnlyLeaf(
        keyword: String,
        pageable: Pageable
    ): Page<CategoryEntity>


    @Query(
        value = "SELECT c " +
                "FROM CategoryEntity c " +
                "WHERE c.id NOT IN (" +
                "   SELECT DISTINCT cs.parent.id " +
                "   FROM CategoryEntity cs " +
                "   WHERE cs.parent.id IS NOT null) " +
                "   AND CONCAT(c.id, ' ', c.name, ' ', c.alias) LIKE %:keyword% " +
                "   AND status = :enabled "
    )
    fun findAllWithKeywordAndStatusAndOnlyLeaf(
        keyword: String,
        enabled: CategoryStatus,
        pageable: Pageable
    ): Page<CategoryEntity>


    fun findByAlias(alias: String): CategoryEntity?
}