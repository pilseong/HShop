package net.philipheur.hshop.common.domain.valueobject

import java.util.UUID

class Country(
    var id: UUID,
    var name: String,
    var code: String,
    var states: List<State>
)