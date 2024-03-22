package net.philipheur.hshop.settingsservice.rest

import net.philipheur.hshop.settingsservice.domain.service.dto.CurrencyDto
import net.philipheur.hshop.settingsservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.settingsservice.domain.service.ports.input.service.SettingService
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.settingsservice.domain.service.dto.SettingDto
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(value = ["/api/settings"])
class SettingController(
    private val settingService: SettingService
) {

    private val log by LoggerDelegator()

    @GetMapping
    fun findAll(): ResponseEntity<SearchResponse<SettingDto>> {

        log.info("Searching all settings")

        val searchResponse = settingService.findAll()

        log.info("{} of settings found", searchResponse.content.size)

        return ResponseEntity.ok(searchResponse)
    }

    @PutMapping(
        consumes = [
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        ]
    )
    fun updateMany(
        @RequestPart("command") settings: List<SettingDto>,
        @RequestPart(
            name = "image",
            required = false
        ) image: MultipartFile?
    ): ResponseEntity<List<SettingDto>> {
        log.info("received settings $settings")

        return ResponseEntity.ok(
            settingService.updateAll(settings)
        )
    }
}