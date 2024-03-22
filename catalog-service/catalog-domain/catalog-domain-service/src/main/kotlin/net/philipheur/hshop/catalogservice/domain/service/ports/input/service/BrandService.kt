package net.philipheur.hshop.catalogservice.domain.service.ports.input.service

import net.philipheur.hshop.catalogservice.domain.service.dto.BrandDto
import net.philipheur.hshop.catalogservice.domain.service.dto.CatalogSearchQuery
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse

interface BrandService {
    fun findAllBrands(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String
    ): SearchResponse<BrandDto>
}