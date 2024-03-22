package net.philipheur.hshop.settingsservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.AggregateRoot
import net.philipheur.hshop.common.domain.entity.BaseEntity
import net.philipheur.hshop.common.domain.valueobject.BrandId
import java.util.UUID

class Currency(
    id: UUID? = null,
    var name: String,
    var symbol: String,
    var code: String,
    var language: String,
    var country: String
) : BaseEntity<UUID>(id)