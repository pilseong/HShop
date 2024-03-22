package net.philipheur.hshop.userservice.domain.service.security

import net.philipheur.hshop.common.domain.valueobject.UserId
import net.philipheur.hshop.common.domain.valueobject.UserStatus
import net.philipheur.hshop.userservice.domain.core.entity.Role
import net.philipheur.hshop.userservice.domain.core.entity.User
import net.philipheur.hshop.userservice.domain.core.exception.UserDuplicatedException
import net.philipheur.hshop.userservice.domain.core.exception.UserNotFoundException
import net.philipheur.hshop.userservice.domain.service.dto.auth.JwtAuthResponse
import net.philipheur.hshop.userservice.domain.service.dto.auth.LoginQuery
import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateCommand
import net.philipheur.hshop.userservice.domain.service.dto.auth.UserCreateResponse
import net.philipheur.hshop.userservice.domain.service.ports.input.service.AuthService
import net.philipheur.hshop.userservice.domain.service.ports.output.repository.RoleRepository
import net.philipheur.hshop.userservice.domain.service.ports.output.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.*

@Service
class AuthServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
) : AuthService {

    override fun login(loginQuery: LoginQuery): JwtAuthResponse {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginQuery.email,
                loginQuery.password
            )
        )

        val user = userRepository.findByEmail(loginQuery.email)
            ?: throw UserNotFoundException("user with email: ${loginQuery.email} not found")

        SecurityContextHolder
            .getContext()
            .authentication = authentication

        return JwtAuthResponse(
            accessToken = jwtTokenProvider.generateToken(
                authentication = authentication,
                user = user
            )
        )
    }


    override fun register(
        command: UserCreateCommand,
        image: MultipartFile?
    ): UserCreateResponse {

        // 이메일 중복확인
        if (isDuplicationEmail(command.email)) {
            throw UserDuplicatedException("Email already used by other user")
        }

        // 롤가져오기
        val roles = mutableListOf<Role>()

        command.roles.forEach {
            val role = roleRepository.findById(it)
            if (role != null) roles.add(role)
        }

        // 권한 확인로직

        // 유저 생성 검증

        // 유저저장 - 나중에 요청자가 누구인지 확인하여 권한 체크가 필요하다
        // 이미지가 있는 경우 이미지 부터 저장

        val userId = UUID.randomUUID()

        // 파일 저장
        val savedFilename =
            if (!command.photo.isNullOrEmpty()) {
                userRepository.savePhotoFile(
                    path = Path.of("photos/$userId"),
                    filename = command.photo,
                    image = image!!
                )
            } else command.photo


        // 유저 저장
        var user = User(
            id = UserId(userId),
            email = command.email,
            firstName = command.firstName,
            lastName = command.lastName,
            photo = if (savedFilename.isNullOrEmpty())
                null else savedFilename,
            password = passwordEncoder.encode(command.password),
            status = UserStatus.valueOf(command.status),
            roles = roles,
            failureMessage = mutableListOf()
        )


        // 유저 저장
        user = userRepository.save(user)

        return UserCreateResponse(
            id = user.id!!.value,
            message = "User Created Successfully"
        )
    }

    private fun isDuplicationEmail(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }
}