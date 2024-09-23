package br.com.projectbank.testcases.service.register

import br.com.projectbank.domain.form.RegisterForm
import br.com.projectbank.repository.ClientRepository
import br.com.projectbank.repository.UserRepository
import br.com.projectbank.service.jwt.JwtService
import br.com.projectbank.service.register.RegisterServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import kotlin.test.Test

@DataJpaTest
class RegisterServiceTest {

    private lateinit var autoCloseable: AutoCloseable
    @MockBean
    lateinit var clientRepository: ClientRepository
    @MockBean
    lateinit var userRepository : UserRepository
    @MockBean
    private lateinit var jwtService: JwtService
    @MockBean
    private lateinit var passwordEncoder: PasswordEncoder
    @InjectMocks
    private lateinit var registerService: RegisterServiceImpl
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    var registerFormError1 = RegisterForm("testeError1","000.000.000-00","12345","", LocalDate.of(2000,7,4),"")
    var registerFormError2 = RegisterForm("testeError2","734.245.300-50","12345","", LocalDate.of(2000,7,4),"")
    @BeforeEach
    fun setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this)
        Mockito.`when`(passwordEncoder.encode(Mockito.anyString())).thenReturn("12345")
    }

    @Test
    fun checkRegisterIdadeValidation() {
        assertThat(registerService.register(registerFormError2)).isNull()
        log.info("Usuario menor de 18 anos - validado")
    }

    @Test
    fun checkRegisterCpfValidation() {
        assertThat(registerService.register(registerFormError1)).isNull()
        log.info("CPF invalido - validado")

    }



}