package net.philipheur.hshop.backendservice.dto.role

import jakarta.persistence.Column

class RoleDto(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RoleDto

        return name == other.name
    }

    override fun hashCode(): Int {
        return name?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "$name"
    }


}