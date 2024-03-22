package net.philipheur.hshop.settingsservice.domain.service.ports.output.repository

import net.philipheur.hshop.settingsservice.domain.core.entity.Currency
import net.philipheur.hshop.settingsservice.domain.service.dto.PageResponse

interface CurrencyRepository {
    fun findAll(): List<Currency>
}