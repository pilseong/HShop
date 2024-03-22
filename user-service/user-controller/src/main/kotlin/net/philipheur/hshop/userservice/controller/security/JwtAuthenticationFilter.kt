package net.philipheur.hshop.userservice.controller.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.philipheur.hshop.userservice.domain.service.security.JwtTokenProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val hshopUserDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // request 헤더로 부터 jwt 토큰 추출
//        var bearer = request.getHeader("Authorization")
//        bearer = if (!bearer.isNullOrEmpty() && bearer.startsWith("Bearer"))
//            bearer.substring(7, bearer.length)
//        else null

//
        val bearer =
            try {
                request.cookies?.first { it.name == "jwt" }
            } catch (e: NoSuchElementException) {
                null
            }

        if (!bearer?.value.isNullOrEmpty() && jwtTokenProvider.validateToken(bearer?.value!!)) {
            // 사용자의 jwt 토큰 검증 완료

            // 데이터 베이스에서 사용자를 검색한다.
            val userDetails = hshopUserDetailsService
                .loadUserByUsername(
                    jwtTokenProvider.username(bearer.value)
                )

            val authToken = UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.authorities
            )
            // 사용자 요청 호출 정보를 추출하여 추가 저장한다. 중요도 없음
            authToken.details = WebAuthenticationDetailsSource()
                .buildDetails(request)

            // 검증된 토큰을 저장한다.
            SecurityContextHolder.getContext().authentication = authToken
        }


        filterChain.doFilter(
            request,
            response
        )
    }
}