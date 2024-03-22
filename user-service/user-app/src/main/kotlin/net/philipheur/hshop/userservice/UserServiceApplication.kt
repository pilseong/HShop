package net.philipheur.hshop.userservice

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@EnableJpaRepositories(
    basePackages = [
        "net.philipheur.hshop.userservice.dataaccess",
    ]
)
@EntityScan(
    basePackages = [
        "net.philipheur.hshop.userservice.dataaccess",
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
open class UserServiceApplication {

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // 필수다

    @Bean
    open fun httpTraceRepository(): HttpExchangeRepository
    = InMemoryHttpExchangeRepository()
}

fun main() {
//    val applicationContext =
    runApplication<UserServiceApplication>()
//    applicationContext.beanDefinitionNames.forEach { println(it) }
}