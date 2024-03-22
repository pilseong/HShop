package net.philipheur.hshop.settingsservice.dataaccess.settings.repository

import net.philipheur.hshop.settingsservice.dataaccess.settings.entity.SettingEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SettingJpaRepository: JpaRepository<SettingEntity, UUID> {
//    fun findByCategory(): List<SettingEntity>
}