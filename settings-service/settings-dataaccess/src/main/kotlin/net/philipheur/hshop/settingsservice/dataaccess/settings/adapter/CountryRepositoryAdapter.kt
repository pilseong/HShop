package net.philipheur.hshop.settingsservice.dataaccess.settings.adapter

import net.philipheur.hshop.settingsservice.dataaccess.settings.entity.CountryEntity
import net.philipheur.hshop.settingsservice.dataaccess.settings.entity.StateEntity
import net.philipheur.hshop.settingsservice.dataaccess.settings.mapper.SettingsDataaccessMapper
import net.philipheur.hshop.settingsservice.dataaccess.settings.repository.CountryJpaRepository
import net.philipheur.hshop.settingsservice.dataaccess.settings.repository.StateJpaRepository
import net.philipheur.hshop.settingsservice.domain.core.entity.Country
import net.philipheur.hshop.settingsservice.domain.core.entity.State
import net.philipheur.hshop.settingsservice.domain.core.exception.CountryNotFoundException
import net.philipheur.hshop.settingsservice.domain.service.ports.output.repository.CountryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CountrycountryRepositoryAdapter(
    private val countryRepository: CountryJpaRepository,
    private val stateRepository: StateJpaRepository,
    private val mapper: SettingsDataaccessMapper
) : CountryRepository {
    override fun findAllByOrderByNameAsc(): List<Country> {
        val entities = countryRepository.findAllByOrderByNameAsc()

        return entities.map {
            mapper.transformCountryEntityToDomain(it)
        }
    }

    override fun create(country: Country) {
        countryRepository.save(
            CountryEntity(
                id = country.id,
                name = country.name,
                code = country.code,
                states = emptySet()
            )
        )
    }

    override fun delete(id: UUID) {
        val optionalEntity = countryRepository.findById(id)
        if (!optionalEntity.isPresent) {
            throw CountryNotFoundException("Country with id: $id not found")
        }
        val entity = optionalEntity.get()
        countryRepository.delete(entity)
    }

    override fun update(id: UUID, country: Country) {
        val optionalEntity = countryRepository.findById(id)
        if (!optionalEntity.isPresent) {
            throw CountryNotFoundException("Country with id: $id not found")
        }
        val entity = optionalEntity.get()

        entity.name = country.name
        entity.code = country.code

        countryRepository.save(entity)
    }

    override fun createState(countryId: UUID, state: State) {
        val optionalEntity = countryRepository.findById(countryId)
        if (!optionalEntity.isPresent) {
            throw CountryNotFoundException("Country with id: $countryId not found")
        }
        val entity = optionalEntity.get()

        stateRepository.save(
            StateEntity(
                id = state.id,
                name = state.name,
                country = entity
            )
        )
    }

    override fun deleteState(stateId: UUID) {
        val optionalEntity = stateRepository.findById(stateId)

        if (!optionalEntity.isPresent) {
            throw CountryNotFoundException("state with id: $stateId not found")
        }
        val entity = optionalEntity.get()

        stateRepository.delete(entity)
    }

    override fun updateState(countryId: UUID, stateId: UUID, state: State) {
        val optionalEntity = stateRepository.findById(stateId)

        if (!optionalEntity.isPresent) {
            throw CountryNotFoundException("state with id: $stateId not found")
        }
        val entity = optionalEntity.get()

        entity.name = state.name

        stateRepository.save(entity)



    }
}