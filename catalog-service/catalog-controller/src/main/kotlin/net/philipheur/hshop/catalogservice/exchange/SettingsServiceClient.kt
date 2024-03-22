//package net.philipheur.hshop.catalogservice.exchange;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse;
//import net.philipheur.hshop.common.domain.dtos.settings.SettingDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.Locale;
//
//
//@FeignClient(name = "settings-service")
//public interface SettingsServiceClient {
//
//    @GetMapping(value = "/api/settings")
//    @CircuitBreaker(name = "settings-service", fallbackMethod = "findAllSettingFallBack")
//    SearchResponse<SettingDto> findAllSettings();
//
//
//    default SearchResponse<SettingDto> findAllSettingFallBack(Throwable exception) {
//
//        System.out.println("circuit break default function executed");
//
//        var locale = Locale.getDefault();
//
//        return new SearchResponse<>(1, 1, 0, 1, true,
//                List.of(new SettingDto(
//                        UUID.randomUUID().toString(),
//                        "CURRENCY",
//                        String.join(",", locale.getLanguage(), locale.getCountry()),
//                        "GENERAL"
//                ))
//        );
//    }
//}

package net.philipheur.hshop.catalogservice.exchange;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import net.philipheur.hshop.catalogservice.domain.service.dto.SearchResponse
import net.philipheur.hshop.common.domain.dtos.settings.SettingDto
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@FeignClient(name = "settings-service", fallback = SettingServiceClientFallback::class)
interface SettingsServiceClient {

    @GetMapping(value = ["/api/settings"])
    fun findAllSettings(): SearchResponse<SettingDto>
}
