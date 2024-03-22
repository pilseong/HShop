package net.philipheur.hshop.userservice.controller.rest.config

import net.philipheur.hshop.userservice.controller.security.JwtAuthenticationEntryPoint
import net.philipheur.hshop.userservice.controller.security.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration



@Configuration
open class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val jwtEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtFilter: JwtAuthenticationFilter,
) {

    @Value("\${gateway.ip}")
    val ip: String = ""


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
            .headers { it.frameOptions { options -> options.sameOrigin() } }
            .authorizeHttpRequests {
                it.requestMatchers("/actuator/**").permitAll()
                it.requestMatchers(HttpMethod.GET, "/photos/**").permitAll()
                it.requestMatchers(HttpMethod.GET, "/api/roles/**").permitAll()
                it.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
//                    .access(WebExpressionAuthorizationManager(
//                        "hasIpAddress('$ip')"))
                it.requestMatchers("/h2-console/**").permitAll()
                it.anyRequest().authenticated();
            }
            .exceptionHandling { it.authenticationEntryPoint(jwtEntryPoint) }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .httpBasic(Customizer.withDefaults())
        httpSecurity.addFilterBefore(
            jwtFilter, UsernamePasswordAuthenticationFilter::class.java
        )

        return httpSecurity.build();
    }

    @Bean
    open fun authenticationManager(
        config: AuthenticationConfiguration
    ): AuthenticationManager = config.authenticationManager


}