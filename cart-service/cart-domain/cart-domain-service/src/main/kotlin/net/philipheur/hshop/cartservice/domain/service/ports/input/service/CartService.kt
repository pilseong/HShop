package net.philipheur.hshop.cartservice.domain.service.ports.input.service

import jakarta.validation.Valid
import net.philipheur.hshop.cartservice.domain.service.dto.search.CartDto
import net.philipheur.hshop.cartservice.domain.service.dto.search.SearchCartCommand
import net.philipheur.hshop.cartservice.domain.service.dto.search.SearchCartResponse
import net.philipheur.hshop.common.domain.dtos.settings.SearchResponse

interface CartService {
    fun fetchCart(
        @Valid command: SearchCartCommand
    ): SearchCartResponse

    fun findCart(searchCartCommand: SearchCartCommand): SearchResponse<CartDto>
}