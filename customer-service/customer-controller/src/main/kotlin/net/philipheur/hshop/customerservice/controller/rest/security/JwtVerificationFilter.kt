package net.philipheur.hshop.customerservice.controller.rest.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.philipheur.hshop.common.utils.security.JwtTokenVerifier
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtVerificationFilter (
    private val tokenVerifier: JwtTokenVerifier,
    ) : OncePerRequestFilter() {

        override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
        ) {

            val bearer =
                try {
                    request.cookies?.first { it.name == "jwt" }
                } catch (e: NoSuchElementException) {
                    null
                }


            if (!bearer?.value.isNullOrEmpty() && tokenVerifier.validateToken(bearer?.value!!)) {
                // 사용자의 jwt 토큰 검증 완료

                val claims = tokenVerifier.claims(bearer.value)
//                claims.forEach{
//                    println("${it.key} ${it.value}")
//                }
                // 데이터 베이스에서 사용자를 검색한다.
//                val userDetails = User.builder()
//                    .username(tokenVerifier.username(bearer.value))
//                    .roles()
//                        tokenVerifier.username(bearer.value)
//                    )
//
//                val authToken = UsernamePasswordAuthenticationToken(
//                    userDetails,
//                    null,
//                    userDetails.authorities
//                )
//                // 사용자 요청 호출 정보를 추출하여 추가 저장한다. 중요도 없음
//                authToken.details = WebAuthenticationDetailsSource()
//                    .buildDetails(request)
//
//                // 검증된 토큰을 저장한다.
//                SecurityContextHolder.getContext().authentication = authToken
            }


            filterChain.doFilter(
                request,
                response
            )
        }
}