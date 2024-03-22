package net.philipheur.hshop.catalogservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.AggregateRoot
import net.philipheur.hshop.common.domain.valueobject.BrandId
import java.time.ZonedDateTime

class Brand(
    id: BrandId? = null,
    var name: String,
    var logo: String,
    var categories: MutableList<Category>,
    var createdAt: ZonedDateTime? = null
) : AggregateRoot<BrandId>(id)