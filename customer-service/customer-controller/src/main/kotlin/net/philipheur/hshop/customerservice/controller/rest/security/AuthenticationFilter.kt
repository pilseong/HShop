package net.philipheur.hshop.customerservice.controller.rest.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.philipheur.hshop.customerservice.domain.service.login.LoginRequestModel
import net.philipheur.hshop.customerservice.domain.service.security.CustomerUserDetails
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException


class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        try {
            val credentials = ObjectMapper().registerModule(KotlinModule.Builder().build())
                .readValue(request.inputStream, LoginRequestModel::class.java)

            return authenticationManager
                .authenticate(
                    UsernamePasswordAuthenticationToken(
                        credentials.email,
                        credentials.password,
                        mutableListOf()
                    )
                )
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException()
//            throw SecurityDomainException("cannot read credentials", e)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = authResult?.principal as CustomerUserDetails
        val accessToken = jwtTokenProvider.generateToken(authResult,  user.customer)

        val jwt = ResponseCookie
            .from("jwt", accessToken)
            .httpOnly(true)
            .maxAge(24 * 60 * 60)
            .path("/")
            .sameSite("None")
            .secure(true)
            .build().toString()

        response?.setHeader(HttpHeaders.SET_COOKIE, jwt)

        val json = ObjectMapper().createObjectNode()
        json.put("email", user.customer.email)
        json.put("firstName", user.customer.firstName)
        json.put("lastName", user.customer.lastName)

        val body = ObjectMapper().writeValueAsBytes(json)
        response?.contentType = "application/json"
        response?.setContentLength(body.size)
        response?.outputStream?.write(body)
    }
}