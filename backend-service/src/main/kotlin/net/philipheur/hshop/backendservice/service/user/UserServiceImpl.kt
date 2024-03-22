package net.philipheur.hshop.backendservice.service.user

import net.philipheur.hshop.backendservice.dto.role.RoleDto
import net.philipheur.hshop.backendservice.dto.user.UserDto
import net.philipheur.hshop.backendservice.entity.Role
import net.philipheur.hshop.backendservice.entity.User
import net.philipheur.hshop.backendservice.exception.user.UserNotFoundException
import net.philipheur.hshop.backendservice.repository.RoleRepository
import net.philipheur.hshop.backendservice.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserService {
    override fun findByEmail(email: String): UserDto? {
        val userEntity = userRepository.findByEmail(email)
        if (userEntity != null)
            return userEntityToDto(userEntity)

        return userEntity
    }

    override fun findById(id: Long): UserDto {
        val result = userRepository.findById(id)
        if (result.isPresent) {
            val userEntity = result.get()
            return userEntityToDto(userEntity)
        } else {
            throw UserNotFoundException("cannot find the user with id $id")
        }
    }

    override fun isAvailableEmail(email: String): Boolean {
        return findByEmail(email) == null
    }

    override fun findAll(): List<UserDto> {
        return userRepository.findAll().map {
            userEntityToDto(it)
        }
    }

    override fun save(userDto: UserDto) {

        val roleEntities = mutableSetOf<Role>()
        userDto.roles.forEach {
            roleEntities.add(roleRepository.findById(it.id!!.toLong()).get())
        }

        val userEntity = User(
            id = 0L,
            email = userDto.email!!,
            password = passwordEncoder.encode(userDto.password!!),
            firstName = userDto.firstName!!,
            lastName = userDto.lastName!!,
            photos = userDto.photos,
            enabled = userDto.enabled!!,
            roles = roleEntities
        )
        userRepository.save(userEntity)
    }

    private fun userEntityToDto(userEntity: User): UserDto {
        return UserDto(
            id = userEntity.id,
            email = userEntity.email,
            firstName = userEntity.firstName,
            lastName = userEntity.lastName,
            photos = userEntity.photos,
            enabled = userEntity.enabled,
            roles = userEntity.roles.map { role ->
                RoleDto(
                    id = role.id,
                    name = role.name,
                    description = role.description
                )
            }.toMutableSet()
        )
    }
}