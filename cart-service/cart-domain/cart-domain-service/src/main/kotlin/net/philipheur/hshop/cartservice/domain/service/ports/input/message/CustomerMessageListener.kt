package net.philipheur.hshop.cartservice.domain.service.ports.input.message

import net.philipheur.hshop.cartservice.domain.service.dto.message.CustomerModel

interface CustomerMessageListener {
    fun customerCreated(customerModel: CustomerModel)
}