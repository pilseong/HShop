package net.philipheur.hshop.cartservice.controller.rest

import jakarta.validation.Valid
import net.philipheur.hshop.cartservice.domain.service.ports.input.service.CartService
import net.philipheur.hshop.common.domain.dtos.settings.SearchResponse
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(
    value = ["/api/carts"],
//    produces = ["application/vnd.api.v1+json"]
)
class CartController(
    private val service: CartService
) {
    private val log by LoggerDelegator()

}