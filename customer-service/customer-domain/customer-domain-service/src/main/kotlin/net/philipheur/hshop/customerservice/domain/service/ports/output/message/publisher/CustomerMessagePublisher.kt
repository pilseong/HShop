package net.philipheur.hshop.customerservice.domain.service.ports.output.message.publisher

import net.philipheur.hshop.customerservice.domain.core.event.CustomerCreatedEvent


interface CustomerMessagePublisher {
    fun publish(customerCreatedEvent: CustomerCreatedEvent)
}