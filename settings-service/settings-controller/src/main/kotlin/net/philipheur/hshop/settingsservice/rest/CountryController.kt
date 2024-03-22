package net.philipheur.hshop.settingsservice.rest

import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.settingsservice.domain.service.dto.CountryDto
import net.philipheur.hshop.settingsservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.settingsservice.domain.service.dto.StateDto
import net.philipheur.hshop.settingsservice.domain.service.ports.input.service.CountryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/settings/countries")
class CountryController(
    private val countryService: CountryService
) {
    private val log by LoggerDelegator()

    @GetMapping
    fun findAllCountries(): ResponseEntity<SearchResponse<CountryDto>> {
        log.info("Searching all currencies")

        val searchResponse = countryService.findAll()

        log.info("{} of currencies found", searchResponse.content.size)

        return ResponseEntity.ok(searchResponse)
    }

    @PostMapping
    fun createCountry(
        @RequestBody country: CountryDto
    ): ResponseEntity<String> {
        log.info("create a country")

        countryService.create(country)

        log.info("country saved successfully")

        return ResponseEntity.ok("country saved successfully")
    }

    @PostMapping("/{id}/states")
    fun createState(
        @PathVariable id: String,
        @RequestBody state: StateDto
    ): ResponseEntity<String> {
        log.info("create a state for country with id: $id")

        countryService.createState(UUID.fromString(id), state)

        log.info("state saved successfully")

        return ResponseEntity.ok("state saved successfully")
    }

    @DeleteMapping("/{id}")
    fun deleteCountry(
        @PathVariable id: String
    ): ResponseEntity<String> {
        log.info("delete a country with id $id")

        countryService.delete(UUID.fromString(id))

        log.info("country deleted successfully")

        return ResponseEntity.ok("country deleted successfully")
    }

    @DeleteMapping("/{countryId}/states/{stateId}")
    fun deleteState(
        @PathVariable countryId: String,
        @PathVariable stateId: String,
    ): ResponseEntity<String> {
        log.info("delete a state with id $stateId")

        countryService
            .deleteState(UUID.fromString(stateId))

        log.info("state deleted successfully")

        return ResponseEntity.ok("state deleted successfully")
    }

    @PutMapping("/{id}")
    fun updateCountry(
        @PathVariable id: String,
        @RequestBody country: CountryDto
    ): ResponseEntity<String> {
        log.info("update a country with id $id")

        countryService.update(
            UUID.fromString(id),
            country
        )

        log.info("country updated successfully")

        return ResponseEntity.ok("country updated successfully")
    }

    @PutMapping("/{countryId}/states/{stateId}")
    fun updateState(
        @PathVariable countryId: String,
        @PathVariable stateId: String,
        @RequestBody state: StateDto,
    ): ResponseEntity<String> {
        log.info("update a state with id $stateId")

        countryService.updateState(
            countryId = UUID.fromString(countryId),
            stateId = UUID.fromString(stateId),
            stateDto = state
        )

        log.info("state updated successfully")

        return ResponseEntity.ok("state updated successfully")
    }
}