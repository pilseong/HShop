package net.philipheur.hshop.cartservice.controller.handler

import net.philipheur.hshop.cartservice.domain.core.exception.CartDomainException
import net.philipheur.hshop.cartservice.domain.core.exception.CartDuplicatedException
import net.philipheur.hshop.cartservice.domain.core.exception.CartNotFoundException
import net.philipheur.hshop.common.controller.ErrorDto
import net.philipheur.hshop.common.controller.GlobalExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.multipart.MaxUploadSizeExceededException

@ControllerAdvice
class CartGlobalExceptionHandler
    : GlobalExceptionHandler() {

    @ResponseBody
    @ExceptionHandler(
        value = [
            CartDomainException::class
        ]
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(
        userDomainException: CartDomainException,
        webRequest: WebRequest
    ): ErrorDto {

        log.error(
            userDomainException.message,
            userDomainException
        )

        return ErrorDto(
            code = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = userDomainException.message!!,
            details = webRequest.getDescription(false)
        )
    }

    @ResponseBody
    @ExceptionHandler(
        value = [
            CartDuplicatedException::class
        ]
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(
        userDuplicatedException: CartDuplicatedException,
        webRequest: WebRequest
    ): ErrorDto {

        log.error(
            userDuplicatedException.message,
            userDuplicatedException
        )

        return ErrorDto(
            code = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = userDuplicatedException.message!!,
            details = webRequest.getDescription(false)
        )
    }

    @ResponseBody
    @ExceptionHandler(
        value = [
            CartNotFoundException::class
        ]
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleException(
        userNotFoundException: CartNotFoundException,
        webRequest: WebRequest
    ): ErrorDto {

        log.error(
            userNotFoundException.message,
            userNotFoundException
        )

        return ErrorDto(
            code = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = userNotFoundException.message!!,
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