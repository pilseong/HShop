package net.philipheur.hshop.settingsservice.dataaccess.settings.repository

import net.philipheur.hshop.settingsservice.dataaccess.settings.entity.CountryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CountryJpaRepository: JpaRepository<CountryEntity, UUID> {
    fun findAllByOrderByNameAsc(): List<CountryEntity>
}