package net.philipheur.hshop.settingsservice.domain.service.ports.input.service

import net.philipheur.hshop.settingsservice.domain.service.dto.CurrencyDto
import net.philipheur.hshop.settingsservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.settingsservice.domain.service.dto.SettingDto

interface SettingService {
    fun findAll(): SearchResponse<SettingDto>
    fun updateAll(command: List<SettingDto>): List<SettingDto>
}