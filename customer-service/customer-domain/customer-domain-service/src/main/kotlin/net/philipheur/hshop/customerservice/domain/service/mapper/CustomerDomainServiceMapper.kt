package net.philipheur.hshop.customerservice.domain.service.mapper

import net.philipheur.hshop.common.domain.dtos.settings.CountryDto
import net.philipheur.hshop.common.domain.dtos.settings.StateDto
import net.philipheur.hshop.customerservice.domain.core.entity.Customer
import net.philipheur.hshop.customerservice.domain.service.dto.search.AddressDto
import net.philipheur.hshop.customerservice.domain.service.dto.search.CustomerDto
import org.springframework.stereotype.Component

@Component
class CustomerDomainServiceMapper {
    fun transformCustomerEntityToDomain(
        customer: Customer
    ): CustomerDto {
        return CustomerDto(
            id = customer.id!!.value,
            email = customer.email,
            firstName = customer.firstName,
            lastName = customer.lastName,
            status = customer.status.name,
            phoneNumber = customer.phoneNumber,
            addresses = customer.addresses.map {
                AddressDto(
                    id = it.id,
                    address1 = it.address1,
                    address2 = it.address2,
                    city = it.city,
                    state = StateDto(
                        id = it.state.stateId,
                        name = it.state.name,
                        country = null
                    ),
                    country = CountryDto(
                        id = it.country.countryId,
                        name = it.country.name,
                        code = it.country.code,
                        states = null
                    ),
                    postalCode = it.postalCode
                )
            },
        )
    }
}