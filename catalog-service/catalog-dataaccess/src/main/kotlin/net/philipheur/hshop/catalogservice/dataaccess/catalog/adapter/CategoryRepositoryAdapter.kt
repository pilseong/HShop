package net.philipheur.hshop.catalogservice.dataaccess.catalog.adapter

import net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper.CategoryDataaccessMapper
import net.philipheur.hshop.catalogservice.dataaccess.catalog.repository.CategoryJpaRepository
import net.philipheur.hshop.catalogservice.domain.core.entity.Category
import net.philipheur.hshop.catalogservice.domain.service.dto.PageResponse
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.CategoryRepository
import net.philipheur.hshop.common.domain.exception.DomainException
import net.philipheur.hshop.common.domain.valueobject.CategoryStatus
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import java.util.*

@Component
class CategoryRepositoryAdapter(
    private val repository: CategoryJpaRepository,
    private val categoryDataaccessMapper: CategoryDataaccessMapper
) : CategoryRepository {
    override fun findAll(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String,
        enabled: String,
        isLeaf: Boolean
    ): PageResponse<Category> {

        val categoryEntities = if (enabled == "ALL") {
            if (isLeaf) {
                repository.findAllWithKeywordOnlyLeaf(
                    keyword = keyword,
                    pageable = pageRequest(
                        pageNo = pageNo,
                        pageSize = pageSize,
                        sortBy = sortBy,
                        orderBy = orderBy,
                    )
                )
            } else {
                repository.findAllWithKeyword(
                    keyword = keyword,
                    pageable = pageRequest(
                        pageNo = pageNo,
                        pageSize = pageSize,
                        sortBy = sortBy,
                        orderBy = orderBy,
                    )
                )
            }
        } else {
            if (isLeaf) {
                repository.findAllWithKeywordAndStatusAndOnlyLeaf(
                    keyword = keyword,
                    pageable = pageRequest(
                        pageNo = pageNo,
                        pageSize = pageSize,
                        sortBy = sortBy,
                        orderBy = orderBy,
                    ),
                    enabled = CategoryStatus.valueOf(enabled)
                )
            } else {
                repository.findAllWithKeywordAndStatus(
                    keyword = keyword,
                    pageable = pageRequest(
                        pageNo = pageNo,
                        pageSize = pageSize,
                        sortBy = sortBy,
                        orderBy = orderBy,
                    ),
                    enabled = CategoryStatus.valueOf(enabled)
                )
            }
        }

        return PageResponse(
            content = categoryEntities.map {
                categoryDataaccessMapper.transformCategoryEntityToDomain(it)
            }.toList(),
            pageNo = categoryEntities.number,
            pageSize = categoryEntities.size,
            totalElements = categoryEntities.totalElements,
            totalPages = categoryEntities.totalPages,
            last = categoryEntities.isLast
        )
    }

    override fun findById(id: UUID): Category {
        val categoryOptional = repository.findById(id)
        if (!categoryOptional.isPresent) {
            throw DomainException("Category with id: $id not found")
        }

        return categoryDataaccessMapper.transformCategoryEntityToDomain(categoryOptional.get())
    }

    override fun findByAlias(alias: String): Category {
        val categoryEntity = repository.findByAlias(alias)
            ?: throw DomainException("Category with alias $alias not found")

        return categoryDataaccessMapper.transformCategoryEntityToDomain(categoryEntity)
    }

    private fun pageRequest(
        pageNo: Int,
        pageSize: Int,
        orderBy: String,
        sortBy: String
    ) = PageRequest.of(
        pageNo,
        pageSize,
        if (orderBy.equals(
                Sort.Direction.ASC.name, true
            )
        ) Sort.by(sortBy).ascending()
        else Sort.by(sortBy).descending()
    )
}