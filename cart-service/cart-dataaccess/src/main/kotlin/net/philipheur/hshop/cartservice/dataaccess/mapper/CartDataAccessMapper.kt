package net.philipheur.hshop.cartservice.dataaccess.mapper

import org.springframework.stereotype.Component

@Component
class CartDataAccessMapper {
//    fun transformCustomerEntityToDomain(customerEntity: CustomerEntity): Cart {
//        return Cart(
//            customerId = CustomerId(customerEntity.id),
//            email = customerEntity.email,
//            password = customerEntity.password,
//            firstName = customerEntity.firstName,
//            lastName = customerEntity.lastName,
//            verificationCode = customerEntity.verificationCode,
//            status = customerEntity.status,
//            phoneNumber = customerEntity.phoneNumber,
//            addresses = customerEntity.customerAddresses.map {
//                CustomerAddress(
//                    id = it.id,
//                    address1 = it.address1,
//                    address2 = it.address2,
//                    city = it.city,
//                    state = CustomerState(
//                        id = it.state.id,
//                        stateId = it.state.stateId,
//                        name = it.state.name
//                    ),
//                    country = CustomerCountry(
//                        id = it.country.id,
//                        countryId = it.country.countryId,
//                        name = it.country.name,
//                        code = it.country.code
//                    ),
//                    postalCode = it.postalCode
//                )
//            },
//        )
//    }
}