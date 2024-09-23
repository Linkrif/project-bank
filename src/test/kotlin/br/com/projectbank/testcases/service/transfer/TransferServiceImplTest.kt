import br.com.projectbank.constants.TransferConstant
import br.com.projectbank.domain.entity.Client
import br.com.projectbank.domain.entity.User
import br.com.projectbank.domain.enums.RoleEnum
import br.com.projectbank.domain.form.TransferForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.repository.ClientRepository
import br.com.projectbank.repository.TransferRepository
import br.com.projectbank.repository.UserRepository
import br.com.projectbank.service.transfer.TransferServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContext

import java.math.BigDecimal
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class TransferServiceImplTest {

    @Mock
    private lateinit var clientRepository: ClientRepository

    @Mock
    private lateinit var transferRepository: TransferRepository

    @Mock
    private lateinit var userRepository: UserRepository
    @Mock
    private lateinit var securityContext: SecurityContext

    @InjectMocks
    private lateinit var transferService: TransferServiceImpl

    private lateinit var form: TransferForm
    private lateinit var client: Client
    private lateinit var clientTo: Client

    @BeforeEach
    fun setUp() {
        form = TransferForm(
            clientTo = "12345678900",
            transferValue = BigDecimal(100),
            transferType = "TRANSFER"
        )
        val testUser1 = User("testUser1","","testUser1", hashSetOf(RoleEnum.USER),false,null)
        client = Client("000000-00","734.245.300-50",
            "testClient1", LocalDate.of(2000,7,4),"",BigDecimal(200.00),"",testUser1)

        val testUser2 = User("testUser2","","testUser2", hashSetOf(RoleEnum.USER),false,null)
        clientTo = Client("000000-00","734.245.300-50",
            "testClient2", LocalDate.of(2000,7,4),"",BigDecimal(50.00),"",testUser2)

    }

    @Test
    fun calculateBalanceShouldReturnCorrectBalance() {
        val result = transferService.calculateBalance(BigDecimal(200), BigDecimal(100))
        assertEquals(BigDecimal(100), result)
    }

    @Test
    fun calculateBalanceShouldThrowStandardExceptionWhenBalanceIsInsufficient() {
        val exception = assertThrows(StandardException::class.java) {
            transferService.calculateBalance(BigDecimal(50), BigDecimal(100))
        }

        assertEquals(TransferConstant.RECUSADO, exception.message)
        assertEquals(HttpStatus.BAD_REQUEST, exception.status)
    }
}
