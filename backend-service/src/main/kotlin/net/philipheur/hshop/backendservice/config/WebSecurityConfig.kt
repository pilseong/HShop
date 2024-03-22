package net.philipheur.hshop.backendservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
open class WebSecurityConfig {
    @Bean
    open fun securityFilterChain(
        httpSecurity: HttpSecurity
    ): SecurityFilterChain {
        httpSecurity
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
//                it.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
//                it.requestMatchers("/api/auth/**").permitAll()
//                it.requestMatchers("/swagger-ui/**").permitAll()
//                it.requestMatchers("/v3/api-docs/**").permitAll()
//                it.anyRequest().authenticated()
            }
//            .exceptionHandling {
//                it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
//            }
//            .sessionManagement {
//                it.sessionCreationPolicy(
//                    SessionCreationPolicy.STATELESS
//                )
//            }
            .httpBasic(Customizer.withDefaults())

//        httpSecurity.addFilterBefore(
//            authenticationFilter, UsernamePasswordAuthenticationFilter::class.java
//        )

        return httpSecurity.build()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}