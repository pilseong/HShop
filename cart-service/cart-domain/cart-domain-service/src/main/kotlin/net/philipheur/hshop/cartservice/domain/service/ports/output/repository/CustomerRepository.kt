package net.philipheur.hshop.cartservice.domain.service.ports.output.repository

import net.philipheur.hshop.cartservice.domain.core.entity.Customer
import java.util.*


interface CustomerRepository {
    fun findCustomer(customerId: UUID): Customer?

    fun save(customer: Customer): Customer
}