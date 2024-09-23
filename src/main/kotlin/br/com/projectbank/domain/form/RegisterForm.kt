package br.com.projectbank.domain.form

import CPF
import br.com.projectbank.exception.StandardException
import br.com.projectbank.utils.AgeUtils
import io.swagger.v3.oas.annotations.Parameter
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotBlank
import org.springframework.http.HttpStatus
import java.time.LocalDate


class RegisterForm (
    @field:NotBlank(message = "Campo nome é obrigatório")
    @Parameter(name="Nome")
    @Length(max = 128, message = "Campo: 'nome' muito longo, max=128 caracters")
    val name: String,
    @field:NotBlank(message = "Campo: 'cpf' is null or blank")
    @field:CPF
    val cpf: String,
    @field:NotBlank(message = "Campo: 'password' vazio")
    @Length(max = 32, message = "Campo: 'password' is too long, max=32 caracters")
    val password: String,
    @Length(max = 32, message = "Campo: 'email' is too long, max=32 caracters")
    val email: String,
    @Length(max = 32, message = "Campo: 'birthDate' is too long, max=32 caracters")
    val birthDate : LocalDate,
    @Length(max = 15, message = "Campo: 'phoneNumber' is too long, max=15 caracters")
    val phoneNumber : String
){

    fun validateRegister(){
        if(AgeUtils.calculateDate(birthDate) < 18){
            throw StandardException("Validação de idade.","Usuário menor de idade.", HttpStatus.BAD_REQUEST)
        }
        if(password ==""){
            throw StandardException("Validação de senha.","Preencha a senha para prosseguir.", HttpStatus.BAD_REQUEST)
        }
    }
}