package net.philipheur.hshop.gatewayservice

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import net.philipheur.hshop.common.utils.security.SecurityDomainException
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app")
object AuthorizationHeaderFilter :
    AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {

    lateinit var jwtSecret: String
//    @Value("\${app.jwt-secret}")
//    val jwtSecret: String = ""

    class Config {}

    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            if (!request.headers.contains("Cookie")) {
                val response = exchange.response
                response.statusCode = HttpStatus.UNAUTHORIZED
                return@GatewayFilter response.setComplete()
            }
            val bearer = request.cookies.getFirst("jwt")

            if (!bearer?.value.isNullOrEmpty() && validateToken(bearer?.value!!)) {
                val subject = verifiedJwtParser()!!
                    .parseSignedClaims(bearer.value)
                    .payload
                    .subject

                if (subject == null) throw SecurityDomainException("invalid token with no subject")
            }

            chain.filter(exchange)
        }
    }

    private fun validateToken(token: String): Boolean {

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