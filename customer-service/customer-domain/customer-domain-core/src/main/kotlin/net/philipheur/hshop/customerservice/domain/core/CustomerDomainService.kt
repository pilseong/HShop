package net.philipheur.hshop.customerservice.domain.core

import net.philipheur.hshop.customerservice.domain.core.entity.Customer
import net.philipheur.hshop.customerservice.domain.core.event.CustomerCreatedEvent

interface CustomerDomainService {
    fun validateAndInitiateCustomer(customer: Customer): CustomerCreatedEvent
}