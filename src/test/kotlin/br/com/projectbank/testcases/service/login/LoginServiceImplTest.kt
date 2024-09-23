package br.com.projectbank.testcases.service.login

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.entity.User
import br.com.projectbank.domain.enums.RoleEnum
import br.com.projectbank.domain.form.LoginForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.repository.UserRepository
import br.com.projectbank.service.jwt.JwtService
import br.com.projectbank.service.login.LoginServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockitoExtension::class)
class LoginServiceImplTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var jwtService: JwtService

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @InjectMocks
    private lateinit var loginService: LoginServiceImpl

    @Test
    fun authUserShouldReturnUserAuthDtoWhenCredentialsAreCorrect() {
        val username = "testuser"
        val password = "password"
        val form = LoginForm(username, password)
        val user = User(username,password,"testUser", hashSetOf(RoleEnum.USER),false,null)
        val userAuthDto = UserAuthDto("Token",setOf(RoleEnum.CLIENT))

        `when`(userRepository.findByUsername(username)).thenReturn(user)
        `when`(passwordEncoder.matches(password, password)).thenReturn(true)
        `when`(jwtService.generateUserAuth(user)).thenReturn(userAuthDto)

        val result = loginService.authUser(form)

        assertNotNull(result)
        assertEquals(userAuthDto, result)
    }

    @Test
    fun authUserShouldThrowStandardExceptionWhenCredentialsAreIncorrect() {
        val username = "testuser"
        val password = "password"
        val encodedPassword = "password"
        val form = LoginForm(username, password)
        val user = User(username,password,"testuser", hashSetOf(RoleEnum.USER),false,null)

        `when`(userRepository.findByUsername(username)).thenReturn(user)
        `when`(passwordEncoder.matches(password, encodedPassword)).thenReturn(false)

        val exception = assertThrows<StandardException> {
            loginService.authUser(form)
        }


        assertEquals("Dados incorretos, tente novamente!", exception.message)
        assertEquals(HttpStatus.BAD_REQUEST, exception.status)
    }
}
