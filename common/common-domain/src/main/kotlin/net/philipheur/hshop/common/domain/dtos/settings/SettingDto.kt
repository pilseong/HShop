package net.philipheur.hshop.common.domain.dtos.settings

import java.util.UUID

data class SettingDto(
    val id: String,
    val key: String,
    val value: String,
    var category: String
)