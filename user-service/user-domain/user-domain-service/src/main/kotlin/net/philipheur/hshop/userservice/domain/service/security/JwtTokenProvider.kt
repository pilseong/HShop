package net.philipheur.hshop.userservice.domain.service.security

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import net.philipheur.hshop.userservice.domain.core.entity.User
import net.philipheur.hshop.userservice.domain.core.exception.UserDomainException
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
        user: User
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
                    "firstName" to user.firstName,
                    "lastName" to user.lastName,
                    "roles" to user.roles.joinToString(",") { it.name },
                    "profile" to mapOf(
                        "id" to user.id!!.value,
                        "firstName" to user.firstName,
                        "lastName" to user.lastName,
                        "photo" to user.photo
                    )
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
            throw UserDomainException(
                "Invalid JWT Token",
                malformed
            )
        } catch (expired: ExpiredJwtException) {
            throw UserDomainException(
                "Expired JWT Token",
                expired
            )
        } catch (unsupported: UnsupportedJwtException) {
            throw UserDomainException(
                "Unsupported JWT Token",
                unsupported
            )
        } catch (illegal: IllegalArgumentException) {
            throw UserDomainException(
                "Jwt Claims is null or empty",
                illegal
            )
        } catch (signature: SignatureException) {
            throw UserDomainException(
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