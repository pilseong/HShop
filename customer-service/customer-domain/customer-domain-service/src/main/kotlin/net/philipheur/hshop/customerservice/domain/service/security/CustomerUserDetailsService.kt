package net.philipheur.hshop.customerservice.domain.service.security

import net.philipheur.hshop.customerservice.domain.service.ports.output.repository.CustomerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomerUserDetailsService(
    private val customerRepository: CustomerRepository
): UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        val customer = customerRepository.findByEmail(email!!)

        return CustomerUserDetails(customer)
    }

}