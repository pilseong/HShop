package net.philipheur.hshop.cartservice.domain.service

import net.philipheur.hshop.cartservice.domain.service.dto.search.CartDto
import net.philipheur.hshop.cartservice.domain.service.dto.search.SearchCartCommand
import net.philipheur.hshop.cartservice.domain.service.dto.search.SearchCartResponse
import net.philipheur.hshop.cartservice.domain.service.ports.input.service.CartService
import net.philipheur.hshop.common.domain.dtos.settings.SearchResponse
import org.springframework.stereotype.Service

@Service
class CartServiceImpl: CartService {
    override fun fetchCart(command: SearchCartCommand): SearchCartResponse {
        TODO("Not yet implemented")
    }

    override fun findCart(searchCartCommand: SearchCartCommand): SearchResponse<CartDto> {
        TODO("Not yet implemented")
    }
}