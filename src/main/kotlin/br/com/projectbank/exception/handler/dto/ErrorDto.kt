package br.com.projectbank.exception.handler.dto

import java.time.Instant


class ErrorDto(
    val timestamp: Instant? = null,
    val status: Int? = null,
    val error: String? = null,
    val message: String? = null,
    val path: String? = null ,
    val extraInfo: Any? = null

) {

}