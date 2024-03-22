package net.philipheur.hshop.configserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
open class ConfigServer
fun main() {
    runApplication<ConfigServer>()
}