package br.com.projectbank.domain.dto

import br.com.projectbank.domain.enums.RoleEnum


class UserAuthDto {
    var token: String? = null
    var roles: Set<RoleEnum>? = null
}