package br.com.projectbank.exception.handler

import br.com.projectbank.exception.StandardException
import br.com.projectbank.exception.handler.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
class ApiExceptionHandler {
    @ExceptionHandler(StandardException::class)
    fun handle(e: StandardException, request: HttpServletRequest): ResponseEntity<ErrorDto> {
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
}