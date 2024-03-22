package net.philipheur.hshop.customerservice.domain.core.exception

import net.philipheur.hshop.common.domain.exception.DomainException


class CustomerDomainException : DomainException {
    constructor(
        message: String
    ) : super(message)

    constructor(
        message: String, cause: Throwable
    ) : super(message, cause)
}
