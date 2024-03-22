package net.philipheur.hshop.customerservice.dataaccess.adapter

import net.philipheur.hshop.common.domain.dtos.settings.PageResponse
import net.philipheur.hshop.customerservice.dataaccess.entity.CustomerEntity
import net.philipheur.hshop.customerservice.dataaccess.mapper.CustomerDataAccessMapper
import net.philipheur.hshop.customerservice.dataaccess.repository.CustomerJpaRepository
import net.philipheur.hshop.customerservice.domain.core.entity.Customer
import net.philipheur.hshop.customerservice.domain.core.exception.CustomerNotFoundException
import net.philipheur.hshop.customerservice.domain.service.ports.output.repository.CustomerRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class CustomerRepositoryAdapter(
    private val customerJpaRepository: CustomerJpaRepository,
    private val mapper: CustomerDataAccessMapper
) : CustomerRepository {
    override fun save(customer: Customer): Customer {
        val customerEntity = customerJpaRepository.save(
            CustomerEntity(
                id = customer.id!!.value,
                email = customer.email,
                password = customer.password,
                firstName = customer.firstName,
                lastName = customer.lastName,
                status = customer.status,
                phoneNumber = customer.phoneNumber,
                customerAddresses = emptySet(),
            )
        )

        return mapper.transformCustomerEntityToDomain(customerEntity)

    }

    override fun findByEmail(email: String): Customer {
        val customerEntity = customerJpaRepository.findByEmail(email)
            ?: throw CustomerNotFoundException("customer with email $email not found")

        return mapper.transformCustomerEntityToDomain(customerEntity)
    }

    override fun findAll(
        pageNo: Int,
        pageSize: Int,
        sortBy: String,
        orderBy: String,
        keyword: String
    ): PageResponse<Customer> {

        val entities = customerJpaRepository.findAllWithKeyword(
            keyword = keyword,
            pageable = pageRequest(
                pageNo = pageNo,
                pageSize = pageSize,
                sortBy = sortBy,
                orderBy = orderBy,
            )
        )

        return PageResponse(
            content = entities.map {
                mapper.transformCustomerEntityToDomain(it)
            }.toList(),
            pageNo = entities.number,
            pageSize = entities.size,
            totalElements = entities.totalElements,
            totalPages = entities.totalPages,
            last = entities.isLast
        )
    }

    private fun pageRequest(
        pageNo: Int,
        pageSize: Int,
        orderBy: String,
        sortBy: String
    ) = PageRequest.of(
        pageNo,
        pageSize,
        if (orderBy.equals(
                Sort.Direction.ASC.name, true
            )
        ) Sort.by(sortBy).ascending()
        else Sort.by(sortBy).descending()
    )

}