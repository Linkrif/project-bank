package br.com.projectbank.domain.form

import br.com.projectbank.exception.StandardException
import br.com.projectbank.utils.AgeUtils
import org.hibernate.validator.constraints.Length
import org.springframework.http.HttpStatus
import java.time.LocalDate
import javax.validation.constraints.NotBlank

class RegisterForm (
    @NotBlank(message = "Campo: 'name' is null or blank")
    @Length(max = 128, message = "Campo: 'name' is too long, max=128 caracters")
    val name: String,
    @NotBlank(message = "Campo: 'cpf' is null or blank")
    @Length(max = 15, message = "Campo: 'login' is too long, max=15 caracters")
    val cpf: String,
    @NotBlank(message = "Campo: 'password' is null or blank")
    @Length(max = 32, message = "Campo: 'password' is too long, max=32 caracters")
    val password: String,
    @Length(max = 32, message = "Campo: 'email' is too long, max=32 caracters")
    val email: String,
    @Length(max = 32, message = "Campo: 'birthDate' is too long, max=32 caracters")
    val birthDate : LocalDate,
    @Length(max = 11, message = "Campo: 'phoneNumber' is too long, max=11 caracters")
    val phoneNumber : String
){

    fun validateRegister(){
        if(AgeUtils.calculateDate(birthDate) < 18){
            throw StandardException("Validação de idade.","Usuário menor de idade.", HttpStatus.BAD_REQUEST)
        }
    }
}