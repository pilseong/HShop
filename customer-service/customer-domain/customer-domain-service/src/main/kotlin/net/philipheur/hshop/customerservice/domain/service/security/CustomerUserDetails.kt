package net.philipheur.hshop.customerservice.domain.service.security

import net.philipheur.hshop.common.domain.valueobject.CustomerStatus
import net.philipheur.hshop.customerservice.domain.core.entity.Customer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomerUserDetails(val customer: Customer): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return customer.password
    }

    override fun getUsername(): String {
        return customer.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return customer.status != CustomerStatus.BLOCKED
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return customer.status == CustomerStatus.ACTIVE
    }
}