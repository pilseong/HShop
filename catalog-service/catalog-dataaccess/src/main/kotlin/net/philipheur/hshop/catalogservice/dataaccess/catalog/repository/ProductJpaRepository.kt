package net.philipheur.hshop.catalogservice.dataaccess.catalog.repository

import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.CategoryEntity
import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ProductJpaRepository : JpaRepository<ProductEntity, UUID> {
    @Query(
        value = "SELECT * " +
                "FROM   catalog.products " +
                "WHERE  search_vector @@ to_tsquery('english', :keyword) " +
                "   AND status='ACTIVE' ",
        nativeQuery = true
    )
    fun findAllWithKeywordFullText(
        keyword: String,
        pageable: Pageable
    ): Page<ProductEntity>

    @Query(
        value = "SELECT p " +
                "FROM   ProductEntity p " +
                "WHERE  CONCAT(p.id, ' ', p.name) LIKE %:keyword% "
    )
    fun findAllWithKeyword(
        keyword: String,
        pageable: Pageable
    ): Page<ProductEntity>


    @Query(
        value = "SELECT p " +
                "FROM   ProductEntity p " +
                "WHERE  p.category in :categories "
    )
    fun findByCategories(
        categories: List<CategoryEntity>,
        pageable: Pageable
    ): Page<ProductEntity>

}