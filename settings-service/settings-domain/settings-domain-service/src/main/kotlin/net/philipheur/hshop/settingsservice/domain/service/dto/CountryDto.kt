package net.philipheur.hshop.settingsservice.domain.service.dto

import net.philipheur.hshop.settingsservice.domain.core.entity.State
import org.intellij.lang.annotations.Language
import java.util.UUID

data class CountryDto(
    val id: UUID?,
    val name: String,
    val code: String,
    val states: List<StateDto>?
)