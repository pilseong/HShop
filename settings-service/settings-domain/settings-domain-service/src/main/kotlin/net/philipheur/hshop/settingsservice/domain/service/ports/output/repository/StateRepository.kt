package net.philipheur.hshop.settingsservice.domain.service.ports.output.repository

import net.philipheur.hshop.settingsservice.domain.core.entity.State

interface StateRepository {
    fun findAll(): List<State>
}