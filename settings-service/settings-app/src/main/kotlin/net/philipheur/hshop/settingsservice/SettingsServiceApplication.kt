package net.philipheur.hshop.settingsservice

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EnableJpaRepositories(
    basePackages = [
        "net.philipheur.hshop.settingsservice.dataaccess",
    ]
)
@EntityScan(
    basePackages = [
        "net.philipheur.hshop.settingsservice.dataaccess",
    ]
)
@SpringBootApplication(
    scanBasePackages = [
        "net.philipheur.hshop"
    ]
)
@ConfigurationPropertiesScan(
    basePackages = [
        "net.philipheur.hshop"
    ]
)
@EnableDiscoveryClient
open class SettingsServiceApplication {
    @Bean
    open fun httpTraceRepository(): HttpExchangeRepository
            = InMemoryHttpExchangeRepository()
}

fun main() {
//    val applicationContext =
    runApplication<SettingsServiceApplication>()
//    applicationContext.beanDefinitionNames.forEach { println(it) }
}