package net.philipheur.hshop.settingsservice.domain.service

import net.philipheur.hshop.settingsservice.domain.core.entity.Country
import net.philipheur.hshop.settingsservice.domain.core.entity.State
import net.philipheur.hshop.settingsservice.domain.service.dto.CountryDto
import net.philipheur.hshop.settingsservice.domain.service.dto.CurrencyDto
import net.philipheur.hshop.settingsservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.settingsservice.domain.service.dto.StateDto
import net.philipheur.hshop.settingsservice.domain.service.ports.input.service.CountryService
import net.philipheur.hshop.settingsservice.domain.service.ports.output.repository.CountryRepository
import net.philipheur.hshop.settingsservice.domain.service.ports.output.repository.CurrencyRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository
) : CountryService {
    override fun findAll(): SearchResponse<CountryDto> {
        val countries = countryRepository.findAllByOrderByNameAsc()

        return SearchResponse(
            totalElements = countries.size.toLong(),
            totalPages = 1,
            pageSize = 100,
            pageNo = 1,
            last = true,
            content = countries.map {
                CountryDto(
                    id = it.id,
                    name = it.name,
                    code = it.code,
                    states = it.states.map { state ->
                        StateDto(
                            id = state.id,
                            name = state.name,
                            country = CountryDto(
                                id = it.id,
                                name = it.name,
                                code = it.code,
                                states = emptyList()
                            )
                        )
                    }
                )
            })
    }

    override fun create(countryDto: CountryDto) {
        countryRepository.create(
            Country(
                id = UUID.randomUUID(),
                name = countryDto.name,
                code = countryDto.code,
                states = emptyList()
            )
        )
    }

    override fun delete(id: UUID) {
        countryRepository.delete(id)
    }

    override fun update(id: UUID, countryDto: CountryDto) {
        countryRepository.update(
            id, Country(
                id = id,
                name = countryDto.name,
                code = countryDto.code,
                states = emptyList()
            )
        )
    }

    override fun createState(countryId: UUID, stateDto: StateDto) {
        countryRepository.createState(
            countryId = countryId,
            state = State(
                id = UUID.randomUUID(),
                name = stateDto.name,
                country = null
            )
        )
    }

    override fun deleteState(stateId: UUID) {
        countryRepository.deleteState(stateId)
    }

    override fun updateState(
        countryId: UUID,
        stateId: UUID,
        stateDto: StateDto
    ) {
        countryRepository.updateState(
            countryId = countryId,
            stateId = stateId,
            state = State(
                id = stateId,
                name = stateDto.name,
                country = null
            )
        )
    }
}
