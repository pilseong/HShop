package net.philipheur.hshop.catalogservice.domain.service.ports.output.repository

import net.philipheur.hshop.catalogservice.domain.core.entity.Category
import net.philipheur.hshop.catalogservice.domain.service.dto.PageResponse
import java.util.UUID

interface CategoryRepository {
    fun findAll(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String,
        enabled: String,
        isLeaf: Boolean
    ): PageResponse<Category>

    fun findById(id: UUID): Category

    fun findByAlias(alias: String): Category
}