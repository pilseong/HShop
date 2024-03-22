package net.philipheur.hshop.customerservice.domain.service.ports.output.repository

import net.philipheur.hshop.common.domain.valueobject.Country
import net.philipheur.hshop.common.domain.valueobject.State
import java.util.UUID

interface SettingsRepository {
    fun findCountryById(countryId: UUID): Country
    fun findStateById(stateId: UUID): State
}