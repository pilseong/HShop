package net.philipheur.hshop.catalogservice.domain.service

import net.philipheur.hshop.catalogservice.domain.service.dto.CatalogSearchQuery
import net.philipheur.hshop.catalogservice.domain.service.dto.CategoryDto
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.catalogservice.domain.service.mapper.CatalogDomainServiceMapper
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.CategoryService
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.CategoryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository,
    private val mapper: CatalogDomainServiceMapper,
) : CategoryService {
    override fun findAllCategories(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        enabled: String,
        isLeaf: Boolean
    ): SearchResponse<CategoryDto> {

        val pageResponse = categoryRepository.findAll(
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
            keyword = query,
            enabled = enabled,
            isLeaf = isLeaf
        )

        return SearchResponse(
            totalElements = pageResponse.totalElements,
            totalPages = pageResponse.totalPages,
            pageNo = pageResponse.pageNo,
            pageSize = pageResponse.pageSize,
            last = pageResponse.last,
            content = pageResponse.content.map {
                mapper.transformCategoryDomainToDto(it)
            }
        )
    }

    override fun findById(id: UUID): SearchResponse<CategoryDto> {
        val category = categoryRepository.findById(id)
        return SearchResponse(
            totalElements = 1,
            totalPages = 1,
            pageNo = 0,
            pageSize = 1,
            last = true,
            content = listOf(mapper.transformCategoryDomainToDto(category))
        )
    }

    override fun findByAlias(alias: String): SearchResponse<CategoryDto> {
        val category = categoryRepository.findByAlias(alias)
        return SearchResponse(
            totalElements = 1,
            totalPages = 1,
            pageNo = 0,
            pageSize = 1,
            last = true,
            content = listOf(mapper.transformCategoryDomainToDto(category))
        )
    }
}