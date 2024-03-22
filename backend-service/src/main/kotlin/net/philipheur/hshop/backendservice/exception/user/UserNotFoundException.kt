package net.philipheur.hshop.backendservice.exception.user

class UserNotFoundException: RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}