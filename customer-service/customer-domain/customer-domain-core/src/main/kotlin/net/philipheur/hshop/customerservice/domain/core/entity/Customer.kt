package net.philipheur.hshop.customerservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.AggregateRoot
import net.philipheur.hshop.common.domain.valueobject.CustomerId
import net.philipheur.hshop.common.domain.valueobject.CustomerStatus
import net.philipheur.hshop.customerservice.domain.core.valueobject.CustomerAddress

class Customer(
    customerId: CustomerId? = null,
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String? = null,
    var verificationCode: String? = null,
    var status: CustomerStatus = CustomerStatus.INACTIVE,
    var phoneNumber: String? = null,
    var addresses: List<CustomerAddress> = listOf(),
) : AggregateRoot<CustomerId>(customerId)