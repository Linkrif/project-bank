package br.com.projectbank.exception.handler

import br.com.projectbank.exception.StandardException
import br.com.projectbank.exception.handler.dto.ErrorDto
import br.com.projectbank.models.ErrorResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.Instant

import javax.servlet.http.HttpServletRequest



@ControllerAdvice
class ApiExceptionHandler {
    @Autowired
    val messageSource: MessageSource? = null

    @ExceptionHandler(StandardException::class)
    fun handle(e: StandardException, request: HttpServletRequest): ResponseEntity<Any?> {
        val status: HttpStatus = e.status
        val error = ErrorDto(
            Instant.now(),
            status.value(),
            e.name,
            e.message,
            request.requestURI,
            e.extraInfo
        )
        return ResponseEntity.status(status).body(error)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException, request: WebRequest): ResponseEntity<Any> {

        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST, "Payload inválido")
        return ResponseEntity.badRequest().body(errorResponse)
    }
}