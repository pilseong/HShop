package net.philipheur.hshop.common.domain.valueobject

import java.util.UUID

class State(
    var id: UUID,
    var name: String,
    var country: Country?,
) {
}