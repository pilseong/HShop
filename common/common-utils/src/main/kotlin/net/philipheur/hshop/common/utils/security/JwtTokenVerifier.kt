package net.philipheur.hshop.common.utils.security

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app")
class JwtTokenVerifier {
    lateinit var jwtSecret: String


    fun username(token: String): String =
        verifiedJwtParser()!!
            .parseSignedClaims(token)
            .payload
            .subject


    fun claims(token: String) =
        verifiedJwtParser()!!
            .parseSignedClaims(token)
            .payload as Map<*, *>

    fun validateToken(token: String): Boolean {

        try {
            verifiedJwtParser()?.parse(token)

            return true

        } catch (malformed: MalformedJwtException) {
            throw SecurityDomainException(
                "Invalid JWT Token",
                malformed
            )
        } catch (expired: ExpiredJwtException) {
            throw SecurityDomainException(
                "Expired JWT Token",
                expired
            )
        } catch (unsupported: UnsupportedJwtException) {
            throw SecurityDomainException(
                "Unsupported JWT Token",
                unsupported
            )
        } catch (illegal: IllegalArgumentException) {
            throw SecurityDomainException(
                "Jwt Claims is null or empty",
                illegal
            )
        } catch (signature: SignatureException) {
            throw SecurityDomainException(
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