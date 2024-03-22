package net.philipheur.hshop.userservice.domain.core.event

import net.philipheur.hshop.userservice.domain.core.entity.User
import java.sql.Timestamp

class UserCreatedEvent(
    user: User,
    createdAt: Timestamp,
) : UserEvent(user, createdAt)