package net.philipheur.hshop.settingsservice.domain.core.entity

import java.util.UUID

class Country(
    var id: UUID,
    var name: String,
    var code: String,
    var states: List<State>
)