package net.philipheur.hshop.customerservice.domain.service

import net.philipheur.hshop.common.domain.dtos.settings.SearchResponse
import net.philipheur.hshop.customerservice.domain.service.dto.create.CreateCustomerCommand
import net.philipheur.hshop.customerservice.domain.service.dto.create.CreateCustomerResponseDto
import net.philipheur.hshop.customerservice.domain.service.dto.search.CustomerDto
import net.philipheur.hshop.customerservice.domain.service.mapper.CustomerDomainServiceMapper
import net.philipheur.hshop.customerservice.domain.service.ports.input.service.CustomerService
import net.philipheur.hshop.customerservice.domain.service.ports.output.message.publisher.CustomerMessagePublisher
import net.philipheur.hshop.customerservice.domain.service.ports.output.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Validated
@Service
open class CustomerServiceImpl(
    private val customerCreateHelper: CustomerCreateHelper,
    private val customerRepository: CustomerRepository,
    private val mapper: CustomerDomainServiceMapper,
    private val publisher: CustomerMessagePublisher
) : CustomerService {

    override fun createCustomer(
        command: CreateCustomerCommand
    ): CreateCustomerResponseDto {

        val event = customerCreateHelper.createCustomer(command)
        publisher.publish(event)

        return CreateCustomerResponseDto(
            customerId = event.customer.id!!.value,
            message = "Customer saved successfully"
        )
    }

    override fun findAllCustomers(
        query: String,
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String
    ): SearchResponse<CustomerDto> {
        val pageResponse = customerRepository.findAll(
            pageNo = pageNo,
            pageSize = pageSize,
            sortBy = sortBy,
            orderBy = orderBy,
            keyword = query
        )

        return SearchResponse(
            totalElements = pageResponse.totalElements,
            totalPages = pageResponse.totalPages,
            pageNo = pageResponse.pageNo,
            pageSize = pageResponse.pageSize,
            last = pageResponse.last,
            content = pageResponse.content.map {
                mapper.transformCustomerEntityToDomain(it)
            }
        )
    }
}