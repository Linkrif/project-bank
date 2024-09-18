package br.com.projectbank.models

import org.springframework.http.HttpStatus

class ErrorResponse(var status: HttpStatus, var message : String)
