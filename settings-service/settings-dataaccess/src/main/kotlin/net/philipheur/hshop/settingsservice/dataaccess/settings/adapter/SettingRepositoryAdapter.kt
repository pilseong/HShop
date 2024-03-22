package net.philipheur.hshop.settingsservice.dataaccess.settings.adapter

import net.philipheur.hshop.settingsservice.dataaccess.settings.mapper.SettingsDataaccessMapper
import net.philipheur.hshop.settingsservice.dataaccess.settings.repository.SettingJpaRepository
import net.philipheur.hshop.settingsservice.domain.core.entity.Setting
import net.philipheur.hshop.settingsservice.domain.service.ports.output.repository.SettingRepository
import org.springframework.stereotype.Service

@Service
class SettingRepositoryAdapter(
    private val repository: SettingJpaRepository,
    private val mapper: SettingsDataaccessMapper
): SettingRepository {
    override fun findAll(): List<Setting> {
        val entities = repository.findAll()

        return entities.map {
            mapper.transformSettingEntityToDomain(it)
        }
    }

    override fun updateMany(settings: List<Setting>): List<Setting> {

        settings.forEach {
            val entityOptional = repository.findById(it.id!!)
            if (entityOptional.isPresent) {
                val entity = entityOptional.get()
                entity.value = it.value
                repository.save(entity)
            }
        }

        val entities = repository.findAll()
        return entities.map {
            mapper.transformSettingEntityToDomain(it)
        }
    }
}