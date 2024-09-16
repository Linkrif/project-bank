package br.com.projectbank.domain.form

import org.hibernate.validator.constraints.Length
import java.util.*
import javax.validation.constraints.NotBlank

class RegisterForm (
    @NotBlank(message = "field 'name' is null or blank")
    @Length(max = 128, message = "field 'name' is too long, max=128 caracters")
    val name: String? = null,
    @NotBlank(message = "field 'cpf' is null or blank")
    @Length(max = 15, message = "field 'login' is too long, max=15 caracters")
    val cpf: String? = null,
    @NotBlank(message = "field 'password' is null or blank")
    @Length(max = 32, message = "field 'password' is too long, max=32 caracters")
    val password: String? = null,
    @Length(max = 32, message = "field 'email' is too long, max=32 caracters")
    val email: String? = null,
    @Length(max = 32, message = "field 'birthDate' is too long, max=32 caracters")
    val birthDate : Date,
    @Length(max = 11, message = "field 'phoneNumber' is too long, max=11 caracters")
    val phoneNumber : String
)