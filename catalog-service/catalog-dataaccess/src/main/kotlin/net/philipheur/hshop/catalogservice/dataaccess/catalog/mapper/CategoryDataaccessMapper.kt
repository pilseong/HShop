package net.philipheur.hshop.catalogservice.dataaccess.catalog.mapper

import net.philipheur.hshop.catalogservice.dataaccess.catalog.entity.CategoryEntity
import net.philipheur.hshop.catalogservice.domain.core.entity.Category
import net.philipheur.hshop.common.domain.valueobject.CategoryId
import org.springframework.stereotype.Component

@Component
class CategoryDataaccessMapper {

    fun transformCategoryDomainToEntity(
        domain: Category
    ): CategoryEntity = CategoryEntity(
        id = domain.id!!.value,
        name = domain.name,
        alias = domain.alias,
        image = domain.image,
        parent = if (domain.parentCategory != null)
            transformCategoryDomainToEntity(domain.parentCategory!!)
        else null,
        status = domain.status,
        subCategories = domain.subCategories.map {
            transformCategoryDomainToEntity(it)
        }.toMutableSet(),
        createdAt = domain.createdAt,
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
}