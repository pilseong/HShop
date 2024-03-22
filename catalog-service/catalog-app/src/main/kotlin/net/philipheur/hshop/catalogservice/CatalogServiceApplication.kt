package net.philipheur.hshop.catalogservice

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EnableJpaRepositories(
    basePackages = [
        "net.philipheur.hshop.catalogservice.dataaccess",
    ]
)
@EntityScan(
    basePackages = [
        "net.philipheur.hshop.catalogservice.dataaccess",
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
@EnableFeignClients
open class CatalogServiceApplication {
    @Bean
    open fun httpTraceRepository(): HttpExchangeRepository
            = InMemoryHttpExchangeRepository()
}

fun main() {
//    val applicationContext =
    runApplication<CatalogServiceApplication>()
//    applicationContext.beanDefinitionNames.forEach { println(it) }
}