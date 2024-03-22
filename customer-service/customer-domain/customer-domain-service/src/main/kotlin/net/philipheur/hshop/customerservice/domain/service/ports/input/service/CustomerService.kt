package net.philipheur.hshop.customerservice.domain.service.ports.input.service

import jakarta.validation.Valid
import net.philipheur.hshop.common.domain.dtos.settings.SearchResponse
import net.philipheur.hshop.common.domain.dtos.settings.SettingDto
import net.philipheur.hshop.customerservice.domain.service.dto.create.CreateCustomerCommand
import net.philipheur.hshop.customerservice.domain.service.dto.create.CreateCustomerResponseDto
import net.philipheur.hshop.customerservice.domain.service.dto.search.CustomerDto

interface CustomerService {
    fun createCustomer(
        @Valid command: CreateCustomerCommand
    ): CreateCustomerResponseDto

    fun findAllCustomers(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
    ): SearchResponse<CustomerDto>
}