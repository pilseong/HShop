package net.philipheur.hshop.catalogservice.domain.service

import net.philipheur.hshop.catalogservice.domain.service.dto.BrandDto
import net.philipheur.hshop.catalogservice.domain.service.dto.CatalogSearchQuery
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.catalogservice.domain.service.mapper.CatalogDomainServiceMapper
import net.philipheur.hshop.catalogservice.domain.service.ports.input.service.BrandService
import net.philipheur.hshop.catalogservice.domain.service.ports.output.repository.BrandRepository
import org.springframework.stereotype.Service

@Service
class BrandServiceImpl(
    private val brandRepository: BrandRepository,
    private val mapper: CatalogDomainServiceMapper
): BrandService {
    override fun findAllBrands(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String
    ): SearchResponse<BrandDto> {
        val pageResponse = brandRepository.findAll(
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
            keyword = query
        )

        return SearchResponse(
            totalElements = pageResponse.totalElements,
            totalPages = pageResponse.totalPages,
            pageNo = pageResponse.pageNo,
            pageSize = pageResponse.pageSize,
            last = pageResponse.last,
            content = pageResponse.content.map {
                mapper.transformBrandDomainToDto(it)
            }
        )
    }
}