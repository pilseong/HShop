package net.philipheur.hshop.cartservice.domain.service.mapper

import net.philipheur.hshop.cartservice.domain.core.entity.Cart
import net.philipheur.hshop.cartservice.domain.service.dto.search.CartDto
import org.springframework.stereotype.Component
import java.util.*

@Component
class CartDomainServiceMapper {
    fun transformCustomerEntityToDomain(
        cart: Cart
    ): CartDto {
        return CartDto(
            id = UUID.randomUUID()
        )
    }
//        return CartDto(
//            id = cart.id!!.value,
//            email = cart.email,
//            firstName = cart.firstName,
//            lastName = cart.lastName,
//            status = cart.status.name,
//            phoneNumber = cart.phoneNumber,
//            addresses = cart.addresses.map {
//                AddressDto(
//                    id = it.id,
//                    address1 = it.address1,
//                    address2 = it.address2,
//                    city = it.city,
//                    state = StateDto(
//                        id = it.state.stateId,
//                        name = it.state.name,
//                        country = null
//                    ),
//                    country = CountryDto(
//                        id = it.country.countryId,
//                        name = it.country.name,
//                        code = it.country.code,
//                        states = null
//                    ),
//                    postalCode = it.postalCode
//                )
//            },
//        )

}