package net.philipheur.hshop.gatewayservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@SpringBootApplication
@EnableDiscoveryClient
open class GatewayServiceApplication

fun main() {
    runApplication<GatewayServiceApplication>()
}

