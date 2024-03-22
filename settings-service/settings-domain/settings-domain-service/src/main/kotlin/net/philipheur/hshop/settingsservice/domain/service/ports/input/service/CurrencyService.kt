package net.philipheur.hshop.settingsservice.domain.service.ports.input.service

import net.philipheur.hshop.settingsservice.domain.service.dto.CurrencyDto
import net.philipheur.hshop.settingsservice.domain.service.dto.SearchResponse

interface CurrencyService {
    fun findAll(): SearchResponse<CurrencyDto>
}