package net.philipheur.hshop.userservice.domain.service.security

import net.philipheur.hshop.userservice.domain.service.ports.output.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    // 어떻게 유저 정보를 데이터 베이스에서 가져올지 정하는 메소드
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username!!)
            ?: throw UsernameNotFoundException("User not found with $username")

        return HShopUserDetails(user = user)
    }
}