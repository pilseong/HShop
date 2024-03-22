package net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper

import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.BrandEntity
import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.CategoryEntity
import net.philipheur.hshop.catalogservice.domain.core.entity.Brand
import net.philipheur.hshop.catalogservice.domain.core.entity.Category
import net.philipheur.hshop.common.domain.valueobject.BrandId
import net.philipheur.hshop.common.domain.valueobject.CategoryId
import org.springframework.stereotype.Component

@Component
class BrandDataaccessMapper(
    private val categoryMapper: CategoryDataaccessMapper
) {
    fun transformBrandDomainToEntity(
        domain: Brand
    ): BrandEntity = BrandEntity(
        id = domain.id!!.value,
        name = domain.name,
        logo = domain.logo,
        categories = domain.categories.map {
            categoryMapper.transformCategoryDomainToEntity(it)
        }.toMutableSet(),
        createdAt = domain.createdAt
    )

    fun transformCategoryEntityToDomain(
        entity: CategoryEntity
    ): Category = Category(
        id = CategoryId(entity.id),
        name = entity.name,
        alias = entity.alias,
        image = entity.image,
        parentCategory = if (entity.parent != null)
            transformCategoryEntityToDomain(entity.parent!!)
        else null,
        status = entity.status,
        subCategories = entity.subCategories.map {
            Category(
                id = CategoryId(it.id),
                name = it.name,
                image = it.image,
                alias = it.alias,
                status = it.status,
                createdAt = it.createdAt
            )
        }.toMutableList(),
        createdAt = entity.createdAt,
    )

    fun transformBrandEntityToDomain(
        entity: BrandEntity
    ) = Brand(
        id = BrandId(entity.id),
        name = entity.name,
        logo = entity.logo,
        categories = entity.categories.map {
            transformCategoryEntityToDomain(it)
        }.toMutableList(),
        createdAt = entity.createdAt
    )
}