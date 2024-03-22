package net.philipheur.hshop.settingsservice.domain.core.entity

import java.util.UUID

class State(
    var id: UUID,
    var name: String,
    var country: Country?,
) {
}