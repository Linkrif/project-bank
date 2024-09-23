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
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    val log: Logger = LoggerFactory.getLogger(this::class.java)
    override fun register(form: RegisterForm): UserAuthDto {
        try {
            checkUserName(form.cpf)
            val user: User = createNewUser(form)
            return jwtService.generateUserAuth(user)
        } catch (e : StandardException){
            throw e
        }

    }

    private fun createNewUser(form: RegisterForm): User {
        log.info("Validando registro")
        form.validateRegister()

        val newUser = User(
            form.cpf,
            passwordEncoder.encode(form.password),
            form.name,
            Collections.singleton(RoleEnum.CLIENT),
            false,
            null
        )
        val client = Client(AccountUtils.generateAccountCode(),form.cpf,form.name,form.birthDate,form.email,
            BigDecimal.ZERO, form.phoneNumber,newUser)

        userRepository.save(newUser)
        clientRepository.save(client)
        log.info("Salvou cliente com sucesso!")
        return newUser
    }

    private fun checkUserName(username: String) {
        if (userRepository.existsByUsername(username) == true) {
            throw StandardException("Validação de usuario.","Usuário já existe.", HttpStatus.BAD_REQUEST)
        }
    }

}