package br.com.projectbank.domain.form

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

class LoginForm (

    @NotBlank(message = "field 'CPF' is null or blank")
    @Length(max = 16, message = "field 'CPF' is too long, max=16 caracters") val cpf: String,
    @NotBlank(message = "field 'password' is null or blank")
    @Length(max = 32, message = "field 'password' is too long, max=32 caracters") val password: String
)