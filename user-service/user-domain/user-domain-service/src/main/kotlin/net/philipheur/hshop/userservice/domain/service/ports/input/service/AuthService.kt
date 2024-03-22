package net.philipheur.hshop.userservice.domain.service.ports.input.service

import net.philipheur.hshop.userservice.domain.service.dto.auth.JwtAuthResponse
import net.philipheur.hshop.userservice.domain.service.dto.auth.LoginQuery
import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateCommand
import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateResponse
import org.springframework.web.multipart.MultipartFile

interface AuthService {
    fun login(loginQuery: LoginQuery): JwtAuthResponse
    fun register(
        command: UserCreateCommand,
        image: MultipartFile?
    ): UserCreateResponse
}