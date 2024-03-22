package net.philipheur.hshop.settingsservice.domain.service

import net.philipheur.hshop.settingsservice.domain.core.entity.Setting
import net.philipheur.hshop.settingsservice.domain.core.valueobject.SettingsCategory
import net.philipheur.hshop.settingsservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.settingsservice.domain.service.dto.SettingDto
import net.philipheur.hshop.settingsservice.domain.service.mapper.SettingsDomainServiceMapper
import net.philipheur.hshop.settingsservice.domain.service.ports.input.service.SettingService
import net.philipheur.hshop.settingsservice.domain.service.ports.output.repository.SettingRepository
import org.springframework.stereotype.Service

@Service
class SettingServiceImpl(
    private val settingsRepository: SettingRepository,
    private val mapper: SettingsDomainServiceMapper
) : SettingService {
    override fun findAll(): SearchResponse<SettingDto> {
        val settings = settingsRepository.findAll()

        return SearchResponse(
            totalElements = settings.size.toLong(),
            totalPages = 1,
            pageSize = 100,
            pageNo = 1,
            last = true,
            content = settings.map {
                mapper.transformSettingDomainToDto(it)
            })
    }

    override fun updateAll(command: List<SettingDto>): List<SettingDto> {
        val updatedSettings = settingsRepository.updateMany(
            command.map {
                mapper.transformSettingDtoToDomain(it)
            }
        )

        return updatedSettings.map {
            mapper.transformSettingDomainToDto(it)
        }
    }
}