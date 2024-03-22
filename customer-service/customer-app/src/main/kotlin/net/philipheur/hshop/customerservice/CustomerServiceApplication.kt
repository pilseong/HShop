package net.philipheur.hshop.customerservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableJpaRepositories(
    basePackages = [
        "net.philipheur.hshop.customerservice.dataaccess",
    ]
)
@EntityScan(
    basePackages = [
        "net.philipheur.hshop.customerservice.dataaccess",
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
open class CustomerServiceApplication {
    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
fun main() {
    runApplication<CustomerServiceApplication>()
}