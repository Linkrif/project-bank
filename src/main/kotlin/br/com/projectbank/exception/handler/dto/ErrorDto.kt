package br.com.projectbank.exception.handler.dto

import java.time.Instant


class ErrorDto(
    private val timestamp: Instant? = null,
    private val status: Int? = null,
    private val error: String? = null,
    private val message: String? = null,
    private val path: String? = null ,
    private val extraInfo: Any? = null

) {

}