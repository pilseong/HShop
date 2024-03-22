package net.philipheur.hshop.userservice.controller.rest

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.userservice.domain.service.dto.auth.JwtAuthResponse
import net.philipheur.hshop.userservice.domain.service.dto.auth.LoginQuery
import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateCommand
import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateResponse
import net.philipheur.hshop.userservice.domain.service.ports.input.service.AuthService
import org.apache.coyote.Response
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping(
    "/api/auth",
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
//@CrossOrigin(
//    origins = [
//        "http://localhost:3000"
//    ],
//    allowedHeaders = ["*"],
//    allowCredentials = "true"
//)
class AuthController(
    private val authService: AuthService
) {
    private val log by LoggerDelegator()

    @PostMapping(value = ["/login", "/signin"])
    fun login(
        @RequestBody @Valid loginQuery: LoginQuery,
        response: HttpServletResponse
    ): ResponseEntity<JwtAuthResponse> {

        log.info("Log in attempt with ${loginQuery.email}")

        val result = authService.login(loginQuery)

        val jwt = ResponseCookie
            .from("jwt", result.accessToken)
            .httpOnly(true)
            .maxAge(24 * 60 * 60)
            .path("/")
            .sameSite("None")
            .secure(true)
            .build().toString()

        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, jwt)
            .body(result)

    }

    @PostMapping(value = ["/logout"])
    fun logout(): ResponseEntity<Map<String, String>> {
        log.info("Log out request")

        val jwt = ResponseCookie
            .from("jwt", "")
            .httpOnly(true)
            .maxAge(0)
            .path("/")
            .sameSite("None")
            .secure(true)
            .build().toString()

        return ResponseEntity
            .ok()
            .header(HttpHeaders.SET_COOKIE, jwt)
            .body(
                mapOf("message" to "logged out successfully")
            )
    }

    @PostMapping(value = ["/register", "signup"])
    fun createUser(
        @RequestPart("command") @Valid command: UserCreateCommand,
        @RequestPart(
            name = "image",
            required = false,
        ) image: MultipartFile?
    ): ResponseEntity<UserCreateResponse> {
        log.info(
            "Creating user with email: {}, role {}",
            command.email, command.roles
        )

        log.info("$command ${image?.originalFilename}")

        val userCreateResponse = authService.register(command, image)

        log.info(
            "User created with " +
                    "id: ${userCreateResponse.id}"
        )

        return ResponseEntity.ok(userCreateResponse)
    }
}