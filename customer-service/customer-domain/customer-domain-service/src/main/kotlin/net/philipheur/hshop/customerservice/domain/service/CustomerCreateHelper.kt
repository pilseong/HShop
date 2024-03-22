package net.philipheur.hshop.customerservice.domain.service

import net.philipheur.hshop.common.domain.valueobject.CustomerId
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.customerservice.domain.core.CustomerDomainService
import net.philipheur.hshop.customerservice.domain.service.dto.create.CreateCustomerCommand
import net.philipheur.hshop.customerservice.domain.service.ports.output.repository.CustomerRepository
import net.philipheur.hshop.customerservice.domain.core.entity.Customer
import net.philipheur.hshop.customerservice.domain.core.event.CustomerCreatedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Component
open class CustomerCreateHelper(
    private val customerRepository: CustomerRepository,
    private val customerDomainService: CustomerDomainService,
    private val passwordEncoder: PasswordEncoder
) {
    private val log by LoggerDelegator()

    @Transactional
    open fun createCustomer(
        command: CreateCustomerCommand
    ): CustomerCreatedEvent {

        // 고객 객체 생성
        val event = customerDomainService.validateAndInitiateCustomer(
            Customer(
                customerId = CustomerId(UUID.randomUUID()),
                email = command.email,
                firstName = command.firstName,
                lastName = command.lastName,
                phoneNumber = command.phoneNumber,
                password = passwordEncoder.encode(command.password)
            )
        )

        customerRepository.save(event.customer)

        log.info(
            "customer has been created with id: ${event.customer.id}")

        return event
    }
}