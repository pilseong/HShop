package net.philipheur.hshop.customerservice.controller.rest.security

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import net.philipheur.hshop.customerservice.domain.core.entity.Customer
import net.philipheur.hshop.customerservice.domain.core.exception.CustomerDomainException
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
@ConfigurationProperties(prefix = "app")
class JwtTokenProvider {
    lateinit var jwtSecret: String
    lateinit var jwtExpirationMilliseconds: String

    fun generateToken(
        authentication: Authentication,
        user: Customer
    ): String {

        return Jwts.builder()
            .subject(authentication.name)
            .issuedAt(Date())
            .expiration(
                Date(
                    Date().time
                        .plus(jwtExpirationMilliseconds.toLong())
                )
            )
            .signWith(
                Keys.hmacShaKeyFor(
                    Decoders.BASE64.decode(jwtSecret)
                )
            )
            .claims(
                mapOf(
                    "email" to user.email,
                    "firstName" to user.firstName,
                    "lastName" to user.lastName,
                )
            )
            .compact()
    }

    fun username(token: String): String =
        verifiedJwtParser()!!
            .parseSignedClaims(token)
            .payload
            .subject

    fun validateToken(token: String): Boolean {

        try {
            verifiedJwtParser()?.parse(token)

            return true

        } catch (malformed: MalformedJwtException) {
            throw CustomerDomainException(
                "Invalid JWT Token",
                malformed
            )
        } catch (expired: ExpiredJwtException) {
            throw CustomerDomainException(
                "Expired JWT Token",
                expired
            )
        } catch (unsupported: UnsupportedJwtException) {
            throw CustomerDomainException(
                "Unsupported JWT Token",
                unsupported
            )
        } catch (illegal: IllegalArgumentException) {
            throw CustomerDomainException(
                "Jwt Claims is null or empty",
                illegal
            )
        } catch (signature: SignatureException) {
            throw CustomerDomainException(
                "JWT token validation did not pass",
                signature
            )
        }
    }

    private fun verifiedJwtParser(): JwtParser? =
        Jwts.parser()
            .verifyWith(
                Keys.hmacShaKeyFor(
                    Decoders.BASE64.decode(jwtSecret)
                )
            ).build()
}