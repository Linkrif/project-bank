package br.com.projectbank.service.deposit

import br.com.projectbank.constants.DepositConstant
import br.com.projectbank.domain.entity.Client
import br.com.projectbank.domain.entity.Deposit
import br.com.projectbank.domain.form.DepositForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.pojo.DepositPojo
import br.com.projectbank.repository.ClientRepository
import br.com.projectbank.repository.DepositRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class DepositServiceImpl : DepositService {
    @Autowired
    private lateinit var clientRepository: ClientRepository
    @Autowired
    private lateinit var depositRepository: DepositRepository

    override fun addDeposit(form: DepositForm) : DepositPojo {
        val clientId : Long = (SecurityContextHolder.getContext().authentication.principal).toString().toLong()
        form.validateDeposit()
        val optionalClient =
            clientRepository.findById(clientId)

        if (optionalClient.isPresent) {
            val client: Client = optionalClient.get()
            client.balance = sumBalance(form.deposit, client.balance)
            clientRepository.save(client)
            depositRepository.save(Deposit(LocalDate.now(),form.deposit,clientId))
            return DepositPojo(true,DepositConstant.EFETUADO)
        } else {
            throw StandardException(DepositConstant.DEPOSIT, DepositConstant.RECUSADO, HttpStatus.BAD_REQUEST)
        }
    }



    fun sumBalance(valorAtual: BigDecimal, novoValor: BigDecimal): BigDecimal {
        return (valorAtual + novoValor);
    }

}