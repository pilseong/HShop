package net.philipheur.hshop.catalogservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.AggregateRoot
import net.philipheur.hshop.common.domain.valueobject.CategoryId
import net.philipheur.hshop.common.domain.valueobject.CategoryStatus
import java.time.ZonedDateTime

class Category(
    id: CategoryId? = null,
    var name: String,
    var alias: String,
    var image: String,
    var parentCategory: Category? = null,
    var status: CategoryStatus,
    var subCategories: MutableList<Category> = mutableListOf(),
    var createdAt: ZonedDateTime? = null
): AggregateRoot<CategoryId>(id)