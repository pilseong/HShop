package net.philipheur.hshop.userservice.domain.core.entity

import net.philipheur.hshop.common.domain.entity.AggregateRoot
import net.philipheur.hshop.common.domain.valueobject.*
import java.sql.Timestamp
import java.time.ZonedDateTime


class User(
    id: UserId? = null,
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var photo: String? = null,
    var status: UserStatus = UserStatus.INACTIVE,
    var roles: MutableList<Role>,
    var createdAt: ZonedDateTime? = null,
    var failureMessage: MutableList<String>? = null
) : AggregateRoot<UserId>(id) {
    fun isAdmin() = !this.roles.none {it.name == "Admin"}

}