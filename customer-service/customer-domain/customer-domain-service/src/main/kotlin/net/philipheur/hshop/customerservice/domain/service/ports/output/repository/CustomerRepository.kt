package net.philipheur.hshop.customerservice.domain.service.ports.output.repository

import net.philipheur.hshop.common.domain.dtos.settings.PageResponse
import net.philipheur.hshop.customerservice.domain.core.entity.Customer


interface CustomerRepository {
    fun save(customer: Customer): Customer
    fun findByEmail(email: String): Customer
    fun findAll(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String,
    ): PageResponse<Customer>
}