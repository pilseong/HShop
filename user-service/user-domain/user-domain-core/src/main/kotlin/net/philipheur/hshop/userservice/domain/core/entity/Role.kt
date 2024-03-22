package net.philipheur.hshop.userservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.BaseEntity
import net.philipheur.hshop.common.domain.valueobject.RoleId
import java.sql.Timestamp
import java.time.ZonedDateTime

class Role(
    id: RoleId? = null,
    var name: String,
    var description: String,
    var createdAt: ZonedDateTime? = null
): BaseEntity<RoleId>(id) {
    override fun toString(): String {
        return "Role(name='$name')"
    }
}