package net.philipheur.hshop.userservice.domain.core.exception

import net.philipheur.hshop.common.domain.exception.DomainException

class UserDuplicatedException: DomainException {
    constructor(
        message: String
    ) : super(message)

    constructor(
        message: String,
        cause: Throwable
    ) : super(message, cause)
}