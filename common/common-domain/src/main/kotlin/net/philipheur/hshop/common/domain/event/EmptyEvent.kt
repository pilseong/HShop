package net.philipheur.hshop.common.domain.event

class EmptyEvent : DomainEvent<Void> {
    companion object {
        val INSTANCE = EmptyEvent()
    }
}