package net.philipheur.hshop.customerservice.domain.core.event

import net.philipheur.hshop.common.domain.event.DomainEvent
import net.philipheur.hshop.customerservice.domain.core.entity.Customer
import java.time.ZonedDateTime

class CustomerCreatedEvent(
    val customer: Customer,
    val createdAt: ZonedDateTime,
) : DomainEvent<Customer>