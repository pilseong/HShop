package net.philipheur.hshop.cartservice.domain.core.exception

import net.philipheur.hshop.common.domain.exception.DomainException


class CartDomainException : DomainException {
    constructor(
        message: String
    ) : super(message)

    constructor(
        message: String, cause: Throwable
    ) : super(message, cause)
}
