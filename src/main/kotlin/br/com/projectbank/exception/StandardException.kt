package br.com.projectbank.exception

import org.springframework.http.HttpStatus


class StandardException : java.lang.RuntimeException {
    var name: String
    var status: HttpStatus
    var extraInfo: Any? = null

    constructor(name: String, message: String?, status: HttpStatus, extra: Any?) : super(message) {
        this.name = name
        this.status = status
        extraInfo = extra
    }

    constructor(name: String, message: String?, status: HttpStatus) : super(message) {
        this.name = name
        this.status = status
    }
}


