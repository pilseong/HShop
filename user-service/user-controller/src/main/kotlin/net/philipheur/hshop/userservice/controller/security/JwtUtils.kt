package net.philipheur.hshop.userservice.controller.security

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.config.Elements.JWT
import java.time.Instant


object JwtUtils {
//    // Private key used to sign and verify the JWT
//    private const val SECRET_KEY = "my-secret-key"
//
//    // Name of the cookie to set and retrieve
//    private const val COOKIE_NAME = "token"
//
//    // Settings for the JWT configuration, like algorithm, verification method and expiry age
//    // In most cases, we can use these settings as is
//    private val JWT_ALGORITHM: Algorithm = Algorithm.HMAC256(SECRET_KEY)
//    private val JWT_VERIFIER: JWTVerifier = JWT.require(JWT_ALGORITHM).build()
//    private const val MAX_AGE_SECONDS = 120
//    private const val MAX_REFRESH_WINDOW_SECONDS = 30
//
//
//    fun generateCookie(username: String?): Cookie {
//        // Create a new JWT token string, with the username embedded in the payload
//        val now = Instant.now()
//        val token: String = JWT.create()
//            .withIssuedAt(now)
//            .withExpiresAt(now.plusSeconds(MAX_AGE_SECONDS.toLong())) // A "claim" is a single payload value which we can set
//            .withClaim("username", username)
//            .sign(JWT_ALGORITHM)
//
//        // Create a cookie with the value set as the token string
//        val jwtCookie = Cookie(COOKIE_NAME, token)
//        jwtCookie.maxAge = MAX_AGE_SECONDS
//        return jwtCookie
//    }
//
//    fun getToken(request: HttpServletRequest): Optional<String> {
//        // Get the cookies from the request
//        val cookies = request.cookies ?: return Optional.empty()
//
//        // Find the cookie with the cookie name for the JWT token
//        for (i in cookies.indices) {
//            val cookie = cookies[i]
//            if (!cookie.name.equals(COOKIE_NAME)) {
//                continue
//            }
//            // If we find the JWT cookie, return its value
//            return Optional.of(cookie.value)
//        }
//        // Return empty if no cookie is found
//        return Optional.empty()
//    }
//
//    fun getValidatedToken(token: String?): Optional<DecodedJWT> {
//        return try {
//            // If the token is successfully verified, return its value
//            Optional.of(JWT_VERIFIER.verify(token))
//        } catch (e: JWTVerificationException) {
//            // If the token can't be verified, return an empty value
//            Optional.empty()
//        }
//    }
//
//    // Gets the expiry timestamp from the request and returns true if it falls
//    // within the allowed window, which starts at a given time before expiry
//    // in this case, 30s
//    fun isRefreshable(request: HttpServletRequest): Boolean {
//        val token: Optional<String> = getToken(request)
//        if (token.isEmpty()) {
//            return false
//        }
//        val expiryTime: Instant = JWT.decode(token.get()).getExpiresAtAsInstant()
//        val canBeRefreshedAfter = expiryTime.minusSeconds(MAX_REFRESH_WINDOW_SECONDS.toLong())
//        return Instant.now().isAfter(canBeRefreshedAfter)
//    }
}