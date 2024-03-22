package net.philipheur.hshop.settingsservice.dataaccess.settings.mapper

import net.philipheur.hshop.settingsservice.dataaccess.settings.entity.CountryEntity
import net.philipheur.hshop.settingsservice.dataaccess.settings.entity.CurrencyEntity
import net.philipheur.hshop.settingsservice.dataaccess.settings.entity.SettingEntity
import net.philipheur.hshop.settingsservice.domain.core.entity.Country
import net.philipheur.hshop.settingsservice.domain.core.entity.Currency
import net.philipheur.hshop.settingsservice.domain.core.entity.Setting
import net.philipheur.hshop.settingsservice.domain.core.entity.State
import org.springframework.stereotype.Component

@Component
class SettingsDataaccessMapper {
    fun transformSettingEntityToDomain(
        entity: SettingEntity
    ) = Setting(
        id = entity.id,
        key = entity.key,
        value = entity.value,
        category = entity.category
    )

    fun transformCurrencyEntityToDomain(
        entity: CurrencyEntity
    ) = Currency(
        id = entity.id,
        name = entity.name,
        symbol = entity.symbol,
        code = entity.code,
        language = entity.language,
        country = entity.country
    )

    fun transformCountryEntityToDomain(
        entity: CountryEntity
    ) = Country(
        id = entity.id,
        name = entity.name,
        code = entity.code,
        states = entity.states.map {
            State(
                id = it.id,
                name = it.name,
                country = Country(
                    id = it.country.id,
                    name = it.country.name,
                    code = it.country.code,
                    states = emptyList()
                )
            )
        }
    )
}