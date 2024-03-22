package net.philipheur.hshop.gatewayservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.cors.reactive.CorsUtils
import org.springframework.web.server.WebFilter
import reactor.core.publisher.Mono


@Configuration
open class CorsConfig {

    private val ORIGINS = listOf(
        "http://localhost:3000",
        "http://localhost:3001",
        "http://localhost:3002",
        "http://192.168.50.167:3000",
        "http://192.168.50.167:3001",
        "http://192.168.50.167:3002",
    )
//    @Bean
//    open fun corsWebFilter(): CorsWebFilter {
//        val corsConfig = CorsConfiguration()
//        corsConfig.allowedOrigins = listOf("http://localhost:3000")
//        corsConfig.maxAge = 3600
//        corsConfig.addAllowedMethod("GET, PUT, POST, DELETE, OPTIONS")
//        corsConfig.addAllowedHeader("*")
//        corsConfig.allowCredentials = true
//
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", corsConfig)
//
////        return CorsWebFilter(source)
//        return CorsCustomFilter(source)
//    }


    @Bean
    open fun corsFilter(): WebFilter {
        return WebFilter { exchange, chain ->
            val request = exchange.request
            val origin = request.headers["Origin"]

            if (!origin.isNullOrEmpty() && CorsUtils.isCorsRequest(request)) {

                val allowed = ORIGINS.first { it == origin[0] }


                val response = exchange.response
                val headers = response.headers
                if (request.method === HttpMethod.OPTIONS) {
                    headers.add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
                    headers.add("Access-Control-Max-Age", "3600")
                    headers.add("Access-Control-Allow-Credentials", "true")
                    headers.add("Access-Control-Allow-Origin", allowed)
//                        ORIGINS.first {it == origin[0]})
                    headers.add(
                        "Access-Control-Allow-Headers",
                        "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN"
                    )
                    response.setStatusCode(HttpStatus.OK)
                    return@WebFilter Mono.empty()
                }
            }
            chain.filter(exchange)
        }
    }
}