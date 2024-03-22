package net.philipheur.hshop.settingsservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.AggregateRoot
import net.philipheur.hshop.settingsservice.domain.core.valueobject.SettingsCategory
import java.util.UUID


class Setting(
    id: UUID,
    var key: String,
    var value: String,
    val category: SettingsCategory
): AggregateRoot<UUID>(id)