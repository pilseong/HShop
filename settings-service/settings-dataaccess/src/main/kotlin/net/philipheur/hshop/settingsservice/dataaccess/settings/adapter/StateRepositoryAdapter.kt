package net.philipheur.hshop.settingsservice.dataaccess.settings.adapter

import net.philipheur.hshop.settingsservice.dataaccess.settings.repository.StateJpaRepository
import net.philipheur.hshop.settingsservice.domain.core.entity.State
import net.philipheur.hshop.settingsservice.domain.service.ports.output.repository.StateRepository
import org.springframework.stereotype.Service

@Service
class StateRepositoryAdapter(
    private val repository: StateJpaRepository
): StateRepository {
    override fun findAll(): List<State> {
        TODO("Not yet implemented")
    }

}