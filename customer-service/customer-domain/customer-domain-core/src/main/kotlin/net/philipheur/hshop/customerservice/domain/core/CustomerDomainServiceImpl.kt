package net.philipheur.hshop.customerservice.domain.core

import net.philipheur.hshop.common.domain.valueobject.CustomerStatus
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.customerservice.domain.core.CustomerDomainService
import net.philipheur.hshop.customerservice.domain.core.entity.Customer
import net.philipheur.hshop.customerservice.domain.core.event.CustomerCreatedEvent
import java.time.ZonedDateTime

class CustomerDomainServiceImpl
    : CustomerDomainService {

    private val log by LoggerDelegator()

    // 고객 생성 이벤트를 생성하고 돌려 준다. 로직이 없다.
    override fun validateAndInitiateCustomer(customer: Customer): CustomerCreatedEvent {
        log.info("Customer with id: ${customer.id!!.value} is initiated")
        customer.status = CustomerStatus.INACTIVE
        return CustomerCreatedEvent(customer, ZonedDateTime.now())
    }
}