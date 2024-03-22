package net.philipheur.hshop.settingsservice.domain.service.ports.output.repository

import net.philipheur.hshop.settingsservice.domain.core.entity.Country
import net.philipheur.hshop.settingsservice.domain.core.entity.State
import java.util.UUID

interface CountryRepository {
    fun findAllByOrderByNameAsc(): List<Country>
    fun create(country: Country)
    fun delete(id: UUID)
    fun update(id: UUID, country: Country)
    fun createState(countryId: UUID, state: State)
    fun deleteState(stateId: UUID)
    fun updateState(countryId: UUID, stateId: UUID, state: State)
}