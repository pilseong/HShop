package net.philipheur.hshop.settingsservice.domain.service.ports.output.repository

import net.philipheur.hshop.settingsservice.domain.core.entity.Currency
import net.philipheur.hshop.settingsservice.domain.core.entity.Setting
import net.philipheur.hshop.settingsservice.domain.service.dto.PageResponse

interface SettingRepository {
    fun findAll(): List<Setting>
    fun updateMany(settings: List<Setting>): List<Setting>
}