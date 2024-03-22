package net.philipheur.hshop.customerservice.dataaccess.mapper

import net.philipheur.hshop.common.domain.valueobject.CustomerId
import net.philipheur.hshop.customerservice.dataaccess.entity.CustomerEntity
import net.philipheur.hshop.customerservice.domain.core.entity.Customer
import net.philipheur.hshop.customerservice.domain.core.valueobject.CustomerAddress
import net.philipheur.hshop.customerservice.domain.core.valueobject.CustomerCountry
import net.philipheur.hshop.customerservice.domain.core.valueobject.CustomerState
import org.springframework.stereotype.Component

@Component
class CustomerDataAccessMapper {
    fun transformCustomerEntityToDomain(customerEntity: CustomerEntity): Customer {
        return Customer(
            customerId = CustomerId(customerEntity.id),
            email = customerEntity.email,
            password = customerEntity.password,
            firstName = customerEntity.firstName,
            lastName = customerEntity.lastName,
            verificationCode = customerEntity.verificationCode,
            status = customerEntity.status,
            phoneNumber = customerEntity.phoneNumber,
            addresses = customerEntity.customerAddresses.map {
                CustomerAddress(
                    id = it.id,
                    address1 = it.address1,
                    address2 = it.address2,
                    city = it.city,
                    state = CustomerState(
                        id = it.state.id,
                        stateId = it.state.stateId,
                        name = it.state.name
                    ),
                    country = CustomerCountry(
                        id = it.country.id,
                        countryId = it.country.countryId,
                        name = it.country.name,
                        code = it.country.code
                    ),
                    postalCode = it.postalCode
                )
            },
        )
    }
}