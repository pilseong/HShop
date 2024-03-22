package net.philipheur.hshop.catalogservice.dataaccess.catalog.adapter

import net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper.BrandDataaccessMapper
import net.philipheur.hshop.catalogservice.dataaccess.catalog.repository.BrandJpaRepository
import net.philipheur.hshop.catalogservice.domain.core.entity.Brand
import net.philipheur.hshop.catalogservice.domain.service.dto.PageResponse
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.BrandRepository
import net.philipheur.hshop.common.domain.exception.DomainException
import net.philipheur.hshop.common.domain.valueobject.BrandId
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BrandRepositoryAdapter(
    private val repository: BrandJpaRepository,
    private val brandDataaccessMapper: BrandDataaccessMapper
): BrandRepository {
    override fun findAll(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String
    ): PageResponse<Brand> {

        val entities = repository.findAllWithKeyword(
            keyword = keyword,
            pageable = pageRequest(
                pageNo = pageNo,
                pageSize = pageSize,
                sortBy = sortBy,
                orderBy = orderBy,
            )
        )

        return PageResponse(
            content = entities.map {
                brandDataaccessMapper.transformBrandEntityToDomain(it)
            }.toList(),
            pageNo = entities.number,
            pageSize = entities.size,
            totalElements = entities.totalElements,
            totalPages = entities.totalPages,
            last = entities.isLast
        )
    }

    override fun findById(brandId: BrandId): Brand {
        val brandOptional = repository.findById(brandId.value)

        return if (brandOptional.isPresent) {
            val brandEntity = brandOptional.get()
            brandDataaccessMapper.transformBrandEntityToDomain(brandEntity)
        } else throw DomainException("cannot find brand with id: ${brandId.value}")
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