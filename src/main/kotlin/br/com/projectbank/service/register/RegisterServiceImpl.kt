package br.com.projectbank.service.register

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.entity.User
import br.com.projectbank.domain.entity.Client
import br.com.projectbank.domain.enums.RoleEnum
import br.com.projectbank.domain.form.RegisterForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.repository.ClientRepository
import br.com.projectbank.repository.UserRepository
import br.com.projectbank.service.jwt.JwtService
import br.com.projectbank.utils.AccountUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*


@Service
class RegisterServiceImpl : RegisterService{

    @Autowired
    lateinit var userRepository : UserRepository
    @Autowired
    lateinit var clientRepository : ClientRepository
    @Autowired
    lateinit var jwtService: JwtService
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder


    override fun register(form: RegisterForm): UserAuthDto {
        checkUserName(form.cpf)
        val user: User = createNewUser(form)
        return jwtService.generateUserAuth(user)
    }

    private fun createNewUser(form: RegisterForm): User {

        form.validateRegister()
        val newUser = User(
            form.cpf,
            passwordEncoder.encode(form.password),
            form.name,
            Collections.singleton(RoleEnum.USER),
            false,
            null
        )
        val client = Client(AccountUtils.generateAccountCode(),form.cpf,form.name,form.birthDate,form.email,
            BigDecimal.ZERO, newUser)

        userRepository.save(newUser)
        clientRepository.save(client)
        return newUser
    }

    private fun checkUserName(username: String) {
        if (userRepository.existsByUsername(username) == true) {
            throw StandardException("Validação de usuario.","Usuário já existe.", HttpStatus.BAD_REQUEST)
        }
    }

}