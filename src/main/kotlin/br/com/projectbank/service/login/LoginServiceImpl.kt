package br.com.projectbank.service.login

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.entity.User
import br.com.projectbank.domain.form.LoginForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.repository.UserRepository
import br.com.projectbank.service.jwt.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class LoginServiceImpl : LoginService{

    @Autowired
    lateinit var userRepository : UserRepository
    @Autowired
    lateinit var jwtService: JwtService
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    override fun authUser(form: LoginForm): UserAuthDto {
        val user: User = userRepository.findByUsername(form.username)

        checkPassword(user, form.password)
        return jwtService.generateUserAuth(user)
    }


    private fun checkPassword(user: User, password: String) {
        if (!passwordEncoder.matches(password, user.password)) {
            throw StandardException("Erro na validação de usuário.","Dados incorretos, tente novamente!", HttpStatus.BAD_REQUEST)
        }
    }
}