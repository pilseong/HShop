package net.philipheur.hshop.discoveryservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
open class DiscoveryServiceApplication

fun main() {
    runApplication<DiscoveryServiceApplication>()
}