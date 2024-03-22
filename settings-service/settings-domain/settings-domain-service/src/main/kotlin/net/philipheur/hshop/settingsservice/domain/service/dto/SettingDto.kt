package net.philipheur.hshop.settingsservice.domain.service.dto

import java.util.UUID

data class SettingDto(
    val id: String,
    val key: String,
    val value: String,
    var category: String
)