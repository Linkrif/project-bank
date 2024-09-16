package br.com.projectbank.service.register

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.entity.User
import br.com.projectbank.domain.enums.RoleEnum
import br.com.projectbank.domain.form.RegisterForm
import br.com.projectbank.repository.UserRepository
import br.com.projectbank.service.jwt.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class RegisterServiceImpl : RegisterService{

    @Autowired
    lateinit var userRepository : UserRepository
    private val jwtService: JwtService? = null
    private val passwordEncoder: PasswordEncoder? = null


    override fun register(form: RegisterForm): UserAuthDto {
        form.cpf?.let { checkCpf(it) }
        val user: User = createNewUser(form)
        return jwtService!!.generateUserAuth(user)
    }

    private fun createNewUser(form: RegisterForm): User {
        val newUser = User(
            form.cpf, passwordEncoder!!.encode(form.password), Collections.singleton(RoleEnum.USER)
        )
        return userRepository.save(newUser)
    }
    private fun checkCpf(cpf: String) {
        if (userRepository.existsByCpf(cpf)) {
            throw RuntimeException("CPF ja cadastrado")
        }
    }

}