package net.philipheur.hshop.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
open class UserDataaccessTester {
    @Bean
    open fun passwordEncoder() = BCryptPasswordEncoder()
}

fun main() {
    runApplication<User>()
}