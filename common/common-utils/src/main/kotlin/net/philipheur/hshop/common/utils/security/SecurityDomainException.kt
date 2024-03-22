package net.philipheur.hshop.common.utils.security

import net.philipheur.hshop.common.domain.exception.DomainException

class SecurityDomainException : DomainException {
    constructor(
        message: String
    ) : super(message)

    constructor(
        message: String, cause: Throwable
    ) : super(message, cause)
}
