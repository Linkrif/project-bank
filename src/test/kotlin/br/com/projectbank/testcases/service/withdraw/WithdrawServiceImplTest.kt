import br.com.projectbank.constants.WithdrawConstant
import br.com.projectbank.domain.entity.Client
import br.com.projectbank.domain.entity.User
import br.com.projectbank.domain.entity.Withdraw
import br.com.projectbank.domain.enums.RoleEnum
import br.com.projectbank.domain.form.WithdrawForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.repository.ClientRepository
import br.com.projectbank.repository.WithdrawRepository
import br.com.projectbank.service.withdraw.WithdrawServiceImpl
import br.com.projectbank.utils.security.PrincipalUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.powermock.core.classloader.annotations.PrepareForTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.http.HttpStatus
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
@PrepareForTest(PrincipalUtils::class)
class WithdrawServiceImplTest {

    @Mock
    private lateinit var clientRepository: ClientRepository

    @Mock
    private lateinit var withdrawRepository: WithdrawRepository

    @Mock
    private lateinit var authentication: Authentication

    @Mock
    private lateinit var securityContext: SecurityContext
    @MockBean
    private lateinit var optionalClient: Optional<Client>
    @InjectMocks
    private lateinit var withdrawService: WithdrawServiceImpl

    private lateinit var form: WithdrawForm
    private lateinit var client: Client

    @BeforeEach
    fun setUp() {
        form = WithdrawForm(
            withdrawValue = BigDecimal(100)
        )
        val testUser = User("testUser1","12345","testUser1", hashSetOf(RoleEnum.USER),false,null)
        client = Client("000000-00","734.245.300-50",
            "testClient1", LocalDate.of(2000,7,4),"",BigDecimal(200.00),"",testUser).apply {
            id = 1L
            balance = BigDecimal(200.00)
        }
    }

    @Test
    fun withdraw() {

        // Simular o contexto de segurança
        `when`(securityContext.authentication).thenReturn(authentication)
        SecurityContextHolder.setContext(securityContext)
        `when`(authentication.principal).thenReturn(1L)
        optionalClient =Optional.of(client)
        `when`(clientRepository.findById(anyLong())).thenReturn(optionalClient)

        val result = withdrawService.withdraw(form)

        assertNotNull(result)
        assertTrue(result.success)
        assertEquals(WithdrawConstant.EFETUADO, result.message)

        verify(clientRepository).save(client)
        verify(withdrawRepository).save(any(Withdraw::class.java))
    }

    @Test
    fun withdrawShouldthrowStandardExceptionWhenClientIsNotFound() {
        `when`(clientRepository.findById(1L)).thenReturn(Optional.empty())

        val exception = assertThrows(StandardException::class.java) {
            withdrawService.withdraw(form)
        }

        assertEquals(WithdrawConstant.RECUSADO, exception.message)
        assertEquals(HttpStatus.BAD_REQUEST, exception.status)
    }

    @Test
    fun `calculateBalance should return correct balance`() {
        val result = withdrawService.calculateBalance(BigDecimal(200), BigDecimal(100))
        assertEquals(BigDecimal(100), result)
    }

}
