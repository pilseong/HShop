package net.philipheur.hshop.settingsservice.domain.service.ports.input.service

import net.philipheur.hshop.settingsservice.domain.service.dto.CountryDto
import net.philipheur.hshop.settingsservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.settingsservice.domain.service.dto.StateDto
import java.util.UUID

interface CountryService {
    fun findAll(): SearchResponse<CountryDto>
    fun create(countryDto: CountryDto)
    fun delete(id: UUID)
    fun update(id: UUID, countryDto: CountryDto)
    fun createState(countryId: UUID, stateDto: StateDto)
    fun deleteState(stateId: UUID)
    fun updateState(countryId: UUID, stateId: UUID, stateDto: StateDto)
}