package net.philipheur.hshop.backendservice.dto.user

import jakarta.persistence.Column
import net.philipheur.hshop.backendservice.dto.role.RoleDto
import net.philipheur.hshop.backendservice.entity.Role

class UserDto(
    var id: Long? = null,
    var email: String? = null,
    var password: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var photos: String? = null,
    var enabled: Boolean? = false,
    var roles: MutableSet<RoleDto> = mutableSetOf()
) {
    override fun toString(): String {
        return "UserDto(id=$id, email=$email, password=$password, firstName=$firstName, lastName=$lastName, photos=$photos, enabled=$enabled, roles=$roles)"
    }
}