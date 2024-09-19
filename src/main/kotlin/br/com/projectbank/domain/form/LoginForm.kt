package br.com.projectbank.domain.form

import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank

class LoginForm (

    @field:NotBlank(message = "field 'username' is null or blank")
    @field:Schema(example= "CPF", description = "CPF do usuário")
    @field:Length(max = 16, message = "field 'username' is too long, max=16 caracters")
    val username: String,
    @field:NotBlank(message = "field 'password' is null or blank")
    @field:Schema(example= "SENHA", description = "Senha de acesso do usuário")
    @field:Length(max = 32, message = "field 'password' is too long, max=32 caracters")
    val password: String
)


