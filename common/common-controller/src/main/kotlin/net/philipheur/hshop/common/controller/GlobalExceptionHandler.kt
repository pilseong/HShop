package net.philipheur.hshop.common.controller

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import jakarta.validation.ValidationException
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest


//@ControllerAdvice
open class GlobalExceptionHandler {

    protected val log by LoggerDelegator()

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected fun handleJsonException(
        ex: HttpMessageNotReadableException,
        webRequest: WebRequest
    ): ErrorDto {

        val words = ex.cause?.localizedMessage?.split(" ")
        val errorMessage = if (words!!.isNotEmpty())
            words[words.indexOf("JSON")+2] + " field: JSON parse error"
        else "JSON parse error"

        return ErrorDto(
            code = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = errorMessage,
            details = webRequest.getDescription(false)
        )

    }

    @ResponseBody
    @ExceptionHandler(
        value = [
            ValidationException::class
        ]
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(
        exception: ValidationException,
        webRequest: WebRequest
    ): ErrorDto {
        val errorDTO: ErrorDto
        if (exception is ConstraintViolationException) {
            val violations = extractViolationsFromException(exception)
            log.error(violations, exception)
            errorDTO = ErrorDto(
                code = HttpStatus.BAD_REQUEST.reasonPhrase,
                message = violations,
                details = webRequest.getDescription(false)
            )
        } else {
            log.error(exception.message)
            errorDTO = ErrorDto(
                code = HttpStatus.BAD_REQUEST.reasonPhrase,
                message = exception.message ?: "",
                details = webRequest.getDescription(false)
            )
        }

        return errorDTO
    }

    @ExceptionHandler(
        value = [
            MethodArgumentNotValidException::class
        ]
    )
    fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        webRequest: WebRequest
    ): ResponseEntity<ErrorDto> {

        val errors: MutableList<String> = ArrayList()
        for (error in exception.bindingResult.fieldErrors) {
            errors.add(error.field + ": " + error.defaultMessage)
        }
        for (error in exception.bindingResult.globalErrors) {
            errors.add(error.objectName + ": " + error.defaultMessage)
        }

        return ResponseEntity(
            ErrorDto(
                code = HttpStatus.BAD_REQUEST.reasonPhrase,
                message = errors.joinToString(","),
                details = webRequest.getDescription(false)
            ),
            HttpStatus.BAD_REQUEST
        )
    }


    @ResponseBody
    @ExceptionHandler(value = [Exception::class])
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(
        exception: Exception,
        webRequest: WebRequest
    ): ErrorDto {
        log.error(exception.message, exception)
        return ErrorDto(
            code = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            message = "Unexpected error",
            details = webRequest.getDescription(false)
        )
    }

    private fun extractViolationsFromException(
        exception: ConstraintViolationException
    ): String {
        return exception.constraintViolations
            .joinToString("--") { it.message }
    }
}