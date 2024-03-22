package net.philipheur.hshop.backendservice.service.user

import net.philipheur.hshop.backendservice.dto.user.UserDto
import net.philipheur.hshop.backendservice.entity.User

interface UserService {
    fun findByEmail(email: String): UserDto?
    fun findById(id: Long): UserDto?
    fun isAvailableEmail(email: String): Boolean
    fun findAll(): List<UserDto>
    fun save(userDto: UserDto)
}