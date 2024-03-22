package net.philipheur.hshop.cartservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(
    basePackages = [
        "net.philipheur.hshop.cartservice.dataaccess",
    ]
)
@EntityScan(
    basePackages = [
        "net.philipheur.hshop.cartservice.dataaccess",
    ]
)
@ConfigurationPropertiesScan(
    basePackages = [
        "net.philipheur.hshop"
    ]
)
@SpringBootApplication(
    scanBasePackages = [
        "net.philipheur.hshop"
    ]
)
@EnableDiscoveryClient
open class CartServiceApplication

fun main() {
    runApplication<CartServiceApplication>()
}