package br.com.projectbank.utils.security

import org.springframework.security.core.context.SecurityContextHolder

object PrincipalUtils {
    fun getId(): Long {
        return SecurityContextHolder.getContext().authentication.principal.toString().toLong()
    }
}