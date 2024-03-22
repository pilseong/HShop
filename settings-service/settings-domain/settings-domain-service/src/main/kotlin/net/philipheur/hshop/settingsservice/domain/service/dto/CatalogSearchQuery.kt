package net.philipheur.hshop.settingsservice.domain.service.dto

import jakarta.validation.constraints.NotEmpty


data class settingsSearchQuery(
    @field:NotEmpty
    val keyword: String
)