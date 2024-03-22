package net.philipheur.hshop.catalogservice.domain.service.ports.output.repository

import net.philipheur.hshop.catalogservice.domain.core.entity.Brand
import net.philipheur.hshop.catalogservice.domain.service.dto.PageResponse
import net.philipheur.hshop.common.domain.valueobject.BrandId

interface BrandRepository {
    fun findAll(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String,
    ): PageResponse<Brand>

    fun findById(brandId: BrandId): Brand
}