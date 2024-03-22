package net.philipheur.hshop.catalogservice.domain.service.ports.input.service

import net.philipheur.hshop.catalogservice.domain.service.dto.CreateProductCommand
import net.philipheur.hshop.catalogservice.domain.service.dto.ProductDto
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.common.domain.dtos.settings.SettingDto

import java.util.UUID

interface ProductService {
    fun findAllProductsByKeyword(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        settings: List<SettingDto>
    ): SearchResponse<ProductDto>

    fun findAllProductsByKeywordFullText(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        settings: List<SettingDto>
    ): SearchResponse<ProductDto>

    fun findByCategoryId(
        categoryId: UUID,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        settings: List<SettingDto>
    ): SearchResponse<ProductDto>

    fun findById(
        id: UUID,
        settings: List<SettingDto>
    ): ProductDto

    fun createProduct(command: CreateProductCommand)
}