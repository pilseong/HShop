package net.philipheur.hshop.catalogservice.exchange

import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.common.domain.dtos.settings.SettingDto
import org.springframework.stereotype.Component
import java.util.*

@Component
class SettingServiceClientFallback : SettingsServiceClient {
    override fun findAllSettings(): SearchResponse<SettingDto> {

        println("circuit break default function executed")

        val locale = Locale.getDefault();

        return SearchResponse(
            totalElements = 1,
            totalPages = 1,
            pageNo = 0,
            pageSize = 1,
            last = true,
            content = listOf(
                SettingDto(
                    id = UUID.randomUUID().toString(),
                    key = "CURRENCY",
                    value = "${locale.language},${locale.country}",
                    category = "GENERAL"
                )
            )
        )
    }
}