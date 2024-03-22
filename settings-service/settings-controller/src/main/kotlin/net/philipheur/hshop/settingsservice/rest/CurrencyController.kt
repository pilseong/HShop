package net.philipheur.hshop.settingsservice.rest

import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.settingsservice.domain.service.dto.CurrencyDto
import net.philipheur.hshop.settingsservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.settingsservice.domain.service.ports.input.service.CurrencyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/currencies")
class CurrencyController(
    private val currencyService: CurrencyService
) {
    private val log by LoggerDelegator()
    @GetMapping
    fun findAll(): ResponseEntity<SearchResponse<CurrencyDto>> {
        log.info("Searching all currencies")

        val searchResponse = currencyService.findAll()

        log.info("{} of currencies found", searchResponse.content.size)

        return ResponseEntity.ok(searchResponse)
    }
}