package net.philipheur.hshop.settingsservice.domain.service

import net.philipheur.hshop.settingsservice.domain.service.dto.CurrencyDto
import net.philipheur.hshop.settingsservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.settingsservice.domain.service.dto.SettingDto
import net.philipheur.hshop.settingsservice.domain.service.mapper.SettingsDomainServiceMapper
import net.philipheur.hshop.settingsservice.domain.service.ports.input.service.CurrencyService
import net.philipheur.hshop.settingsservice.domain.service.ports.output.repository.CurrencyRepository
import org.springframework.stereotype.Service

@Service
class CurrencyServiceImpl(
    private val currencyRepository: CurrencyRepository,
    private val mapper: SettingsDomainServiceMapper,
): CurrencyService {
    override fun findAll(): SearchResponse<CurrencyDto> {
        val currencies = currencyRepository.findAll()

        return SearchResponse(
            totalElements = currencies.size.toLong(),
            totalPages = 1,
            pageSize = 100,
            pageNo = 1,
            last = true,
            content = currencies.map {
                CurrencyDto(
                    id = it.id!!,
                    name = it.name,
                    symbol = it.symbol,
                    code = it.code,
                    language = it.language,
                    country = it.country
                )
            })
    }

}