package net.philipheur.hshop.customerservice.domain.core.valueobject

import java.util.UUID

data class CustomerState (
    val id: UUID,
    val stateId: UUID,
    val name: String
)