package net.philipheur.hshop.catalogservice.config

import net.philipheur.hshop.catalogservice.security.JwtVerificationFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


@Configuration
open class SecurityConfig(
    private val jwtFilter: JwtVerificationFilter,
) {
    @Bean
    open fun securityFilterChain(
        httpSecurity: HttpSecurity
    ): SecurityFilterChain {
        httpSecurity
            .csrf { csrf -> csrf.disable() }
            .cors {
                it.configurationSource {
                    val corsConfig = CorsConfiguration()
                    corsConfig.applyPermitDefaultValues()
                    corsConfig.allowCredentials = true
                    corsConfig.allowedOrigins = listOf(
                        "http://192.168.50.167:3000",
                        "http://192.168.50.167:3001",
                        "http://192.168.50.167:3002",
                        "http://localhost:3000",
                        "http://localhost:3001",
                        "http://localhost:3002"
                    )
                    corsConfig.allowedMethods = listOf("GET", "POST", "PUT", "OPTIONS", "DELETE")
                    corsConfig.allowedHeaders = listOf("*")
                    corsConfig
                }
            }
            .authorizeHttpRequests {
                it.requestMatchers("/**").permitAll();
//                it.requestMatchers(HttpMethod.GET, "/photos/**").permitAll()
                it.anyRequest().authenticated();
            }
            .httpBasic(Customizer.withDefaults())

        httpSecurity.addFilterBefore(
            jwtFilter, UsernamePasswordAuthenticationFilter::class.java
        )

        return httpSecurity.build()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}