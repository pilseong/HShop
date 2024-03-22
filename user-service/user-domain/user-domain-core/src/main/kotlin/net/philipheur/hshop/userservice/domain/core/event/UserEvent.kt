package net.philipheur.hshop.userservice.domain.core.event

import net.philipheur.hshop.common.domain.event.DomainEvent
import net.philipheur.hshop.userservice.domain.core.entity.User
import java.sql.Timestamp

abstract class UserEvent(
    val user: User,
    val createdAt: Timestamp
): DomainEvent<User> {
}