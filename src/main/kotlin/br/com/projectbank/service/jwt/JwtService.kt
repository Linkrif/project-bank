package br.com.projectbank.service.jwt

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.entity.User

interface JwtService {
    fun generateUserAuth(user: User): UserAuthDto
    fun decodeToken(token: String?): DecodedJWT
}