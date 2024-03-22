package net.philipheur.hshop.catalogservice.domain.service.ports.output.repository

import net.philipheur.hshop.catalogservice.domain.core.entity.Product
import net.philipheur.hshop.catalogservice.domain.service.dto.PageResponse

import java.util.UUID

interface ProductRepository {
    fun findAllByKeywordFullText(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String,
    ): PageResponse<Product>

    fun findAllByKeyword(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String,
    ): PageResponse<Product>

    fun findByCategoryId(
        categoryId: UUID,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
    ): PageResponse<Product>

    fun findById(id: UUID): Product

    fun createProduct(product: Product): Product
}