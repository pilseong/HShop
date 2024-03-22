package net.philipheur.hshop.userservice.domain.service.security

import net.philipheur.hshop.common.domain.valueobject.UserStatus
import net.philipheur.hshop.userservice.domain.core.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class HShopUserDetails(
    private val user: User
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val roles = user.roles

        return roles.map {
            SimpleGrantedAuthority(it.name)
        }.toMutableList()
    }

    override fun getPassword() = user.password

    override fun getUsername() = user.email

    override fun isAccountNonExpired() = user.status != UserStatus.INACTIVE

    override fun isAccountNonLocked() = user.status != UserStatus.INACTIVE

    // 나중에 jwt 토큰을 강화한 access 토큰을 db에 저장할 경우 사용 고려
    override fun isCredentialsNonExpired() = user.status != UserStatus.INACTIVE

    override fun isEnabled() = user.status != UserStatus.INACTIVE
}