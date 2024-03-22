package net.philipheur.hshop.catalogservice.domain.service.dto

import net.philipheur.hshop.common.domain.valueobject.CategoryStatus
import java.time.ZonedDateTime

import java.util.UUID

data class CategoryDto (
    val id: UUID,
    val name: String,
    val alias: String,
    val image: String,
    val parentCategory: CategoryDto? = null,
    val status: CategoryStatus,
    val subCategories: List<CategoryDto>,
    val createdAt: ZonedDateTime?
)