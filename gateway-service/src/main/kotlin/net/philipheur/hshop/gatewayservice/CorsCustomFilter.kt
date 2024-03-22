package net.philipheur.hshop.gatewayservice

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.CorsUtils
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

class CorsCustomFilter(configSource: CorsConfigurationSource) : CorsWebFilter(configSource) {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request
        if (CorsUtils.isCorsRequest(request)) {
            val response = exchange.response
            val headers = response.headers
            headers.add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS")
            headers.add("Access-Control-Max-Age", "3600")
            if (request.method === HttpMethod.OPTIONS) {
                headers.add("Access-Control-Allow-Credentials", "true")
                headers.add("Access-Control-Allow-Origin", "http://localhost:3000")
                headers.add(
                    "Access-Control-Allow-Headers",
                    "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN"
                )
                response.setStatusCode(HttpStatus.OK)
                return Mono.empty()
            }
        }
        return chain.filter(exchange);
    }
}