package net.philipheur.hshop.settingsservice.domain.core.exception

import net.philipheur.hshop.common.domain.exception.DomainException

class SettingsDomainException : DomainException {
    constructor(
        message: String
    ) : super(message)

    constructor(
        message: String, cause: Throwable
    ) : super(message, cause)
}
