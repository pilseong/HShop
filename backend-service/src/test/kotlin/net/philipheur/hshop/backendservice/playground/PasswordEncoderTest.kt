package net.philipheur.hshop.backendservice.playground

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import kotlin.test.Test
import kotlin.test.assertTrue


class PasswordEncoderTest {
    @Test
    fun testPasswordEncoder() {
        val passwordEncoder = BCryptPasswordEncoder()
        val password = "qwe123"
        val encodedPassword = passwordEncoder.encode(password)

        assertTrue {
            passwordEncoder.matches(password,encodedPassword)
        }

    }

}