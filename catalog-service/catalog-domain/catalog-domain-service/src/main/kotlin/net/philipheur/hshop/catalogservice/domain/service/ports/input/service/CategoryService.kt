package net.philipheur.hshop.catalogservice.domain.service.ports.input.service

import net.philipheur.hshop.catalogservice.domain.service.dto.CatalogSearchQuery
import net.philipheur.hshop.catalogservice.domain.service.dto.CategoryDto
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import java.util.UUID

interface CategoryService {
    fun findAllCategories(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        enabled: String,
        isLeaf: Boolean
    ): SearchResponse<CategoryDto>

    fun findById(id: UUID): SearchResponse<CategoryDto>

    fun findByAlias(alias: String): SearchResponse<CategoryDto>
}