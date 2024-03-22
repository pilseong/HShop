package net.philipheur.hshop.settingsservice.dataaccess.settings.repository

import net.philipheur.hshop.settingsservice.dataaccess.settings.entity.CurrencyEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CurrencyJpaRepository: JpaRepository<CurrencyEntity, UUID> {
    fun findAllByOrderByNameAsc(): List<CurrencyEntity>
}