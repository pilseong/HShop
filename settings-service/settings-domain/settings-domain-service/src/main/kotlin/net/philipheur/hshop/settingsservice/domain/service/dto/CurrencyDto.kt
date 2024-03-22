package net.philipheur.hshop.settingsservice.domain.service.dto

import org.intellij.lang.annotations.Language
import java.util.UUID

data class CurrencyDto(
    val id: UUID,
    val name: String,
    val symbol: String,
    val code: String,
    val language: String,
    val country: String
)