package net.philipheur.hshop.userservice.controller.rest.exception.handler

import net.philipheur.hshop.common.controller.ErrorDto
import net.philipheur.hshop.common.controller.GlobalExceptionHandler
import net.philipheur.hshop.settingsservice.domain.core.exception.CountryNotFoundException
import net.philipheur.hshop.settingsservice.domain.core.exception.SettingsDomainException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.multipart.MaxUploadSizeExceededException


@ControllerAdvice
class SettingsGlobalExceptionHandler
    : GlobalExceptionHandler() {
    @ResponseBody
    @ExceptionHandler(
        value = [
            SettingsDomainException::class
        ]
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleException(
        settingsDomainException: SettingsDomainException,
        webRequest: WebRequest
    ): ErrorDto {

        log.error(
            settingsDomainException.message,
            settingsDomainException
        )

        return ErrorDto(
            code = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = settingsDomainException.message!!,
            details = webRequest.getDescription(false)
        )
    }

    @ResponseBody
    @ExceptionHandler(
        value = [
            CountryNotFoundException::class
        ]
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleException(
        countryNotFoundException: CountryNotFoundException,
        webRequest: WebRequest
    ): ErrorDto {

        log.error(
            countryNotFoundException.message,
            countryNotFoundException
        )

        return ErrorDto(
            code = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = countryNotFoundException.message!!,
            details = webRequest.getDescription(false)
        )
    }



    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleFileSizeException(
        maxUploadSizeExceededException: MaxUploadSizeExceededException,
        webRequest: WebRequest
    ): ErrorDto {

        log.error(
            maxUploadSizeExceededException.message,
            maxUploadSizeExceededException
        )

        return ErrorDto(
            code = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = maxUploadSizeExceededException.message!!,
            details = webRequest.getDescription(false)
        )
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(
        badCredentialsException: BadCredentialsException,
        webRequest: WebRequest
    ): ErrorDto {
        log.error(
            badCredentialsException.message,
            badCredentialsException
        )

        return ErrorDto(
            code = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = badCredentialsException.message!!,
            details = webRequest.getDescription(false)
        )
    }
}