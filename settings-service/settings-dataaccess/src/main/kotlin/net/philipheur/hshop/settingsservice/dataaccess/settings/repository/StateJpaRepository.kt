package net.philipheur.hshop.settingsservice.dataaccess.settings.repository

import net.philipheur.hshop.settingsservice.dataaccess.settings.entity.StateEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface StateJpaRepository: JpaRepository<StateEntity, UUID> {
}