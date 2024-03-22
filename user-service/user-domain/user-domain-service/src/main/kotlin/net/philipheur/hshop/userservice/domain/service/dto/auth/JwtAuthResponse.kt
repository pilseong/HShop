package net.philipheur.hshop.userservice.domain.service.dto.auth

data class JwtAuthResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)