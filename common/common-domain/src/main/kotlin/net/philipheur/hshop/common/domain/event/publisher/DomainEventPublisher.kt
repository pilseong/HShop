package net.philipheur.hshop.common.domain.event.publisher


interface DomainEventPublisher<in T> {
    fun publish(domainEvent: T)
}
