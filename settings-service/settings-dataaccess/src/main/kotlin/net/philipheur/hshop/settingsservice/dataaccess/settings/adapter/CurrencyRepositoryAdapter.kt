package net.philipheur.hshop.settingsservice.dataaccess.settings.adapter

import net.philipheur.hshop.settingsservice.dataaccess.settings.mapper.SettingsDataaccessMapper
import net.philipheur.hshop.settingsservice.dataaccess.settings.repository.CurrencyJpaRepository
import net.philipheur.hshop.settingsservice.domain.core.entity.Currency
import net.philipheur.hshop.settingsservice.domain.service.ports.output.repository.CurrencyRepository
import org.springframework.stereotype.Component

@Component
class CurrencyRepositoryAdapter(
    private val repository: CurrencyJpaRepository,
    private val mapper: SettingsDataaccessMapper
): CurrencyRepository {
    override fun findAll(): List<Currency> {
        val entities = repository.findAllByOrderByNameAsc()

        return entities.map {
            mapper.transformCurrencyEntityToDomain(it)
        }
    }

}