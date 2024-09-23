package br.com.projectbank.testcases.service.balance

import br.com.projectbank.constants.DepositConstant
import br.com.projectbank.domain.entity.Client
import br.com.projectbank.domain.entity.Deposit
import br.com.projectbank.domain.entity.User
import br.com.projectbank.domain.enums.RoleEnum
import br.com.projectbank.domain.form.BalanceForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.repository.ClientRepository
import br.com.projectbank.repository.DepositRepository
import br.com.projectbank.repository.UserRepository
import br.com.projectbank.service.balance.BalanceServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
class BalanceServiceImplTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var clientRepository: ClientRepository

    @Mock
    private lateinit var depositRepository: DepositRepository

    @InjectMocks
    private lateinit var balanceService: BalanceServiceImpl

    @Test
    fun addBalanceShouldExecuteSuccessfully() {
        val clientId = 1L
        val cpf = "734.245.300-50"
        val nome = "teste"
        val depositAmount = BigDecimal(100)
        val initialBalance = BigDecimal(200)
        val form = BalanceForm(depositAmount)
        val user = User(cpf,"12345",nome, hashSetOf(RoleEnum.CLIENT),false,null)
        val client = Client("000000-00",cpf,
            "testClient", LocalDate.of(2000,7,4),"",initialBalance,"",user)
        user.client = client

        val authentication: Authentication = mock(Authentication::class.java)
        val securityContext: SecurityContext = mock(SecurityContext::class.java)

        `when`(authentication.principal).thenReturn(clientId.toString())
        `when`(securityContext.authentication).thenReturn(authentication)
        SecurityContextHolder.setContext(securityContext)

        `when`(userRepository.findById(clientId)).thenReturn(Optional.of(user))

        val result = balanceService.addBalance(form)

        assertNotNull(result)
        assertTrue(result.success)
        assertEquals(DepositConstant.EFETUADO, result.message)
        assertEquals(initialBalance + depositAmount, client.balance)

        verify(clientRepository).save(client)
        verify(depositRepository).save(any(Deposit::class.java))
    }

    @Test
    fun addBalanceShouldThrowStandardExceptionWhenUserNotFound() {
        val clientId = 1L
        val depositAmount = BigDecimal(100)
        val form = BalanceForm(depositAmount)

        val authentication: Authentication = mock(Authentication::class.java)
        val securityContext: SecurityContext = mock(SecurityContext::class.java)

        `when`(authentication.principal).thenReturn(clientId.toString())
        `when`(securityContext.authentication).thenReturn(authentication)
        SecurityContextHolder.setContext(securityContext)

        `when`(userRepository.findById(clientId)).thenReturn(Optional.empty())

        val exception = assertThrows<StandardException> {
            balanceService.addBalance(form)
        }

        assertEquals(DepositConstant.RECUSADO, exception.message)
        assertEquals(HttpStatus.BAD_REQUEST, exception.status)
    }
}
