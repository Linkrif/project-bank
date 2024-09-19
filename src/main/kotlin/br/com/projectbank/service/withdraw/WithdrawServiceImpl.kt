package br.com.projectbank.service.withdraw

import br.com.projectbank.constants.WithdrawConstant
import br.com.projectbank.domain.entity.Client
import br.com.projectbank.domain.entity.Withdraw
import br.com.projectbank.domain.form.WithdrawForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.pojo.WithdrawPojo
import br.com.projectbank.repository.ClientRepository

import br.com.projectbank.repository.WithdrawRepository
import br.com.projectbank.utils.security.PrincipalUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class WithdrawServiceImpl : WithdrawService {
    @Autowired
    private lateinit var clientRepository: ClientRepository
    @Autowired
    private lateinit var withdrawRepository: WithdrawRepository
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun withdraw(form: WithdrawForm) : WithdrawPojo {
        val optionalClient = clientRepository.findById(PrincipalUtils.getId())
        log.info("inicou validate do saque")

        form.validateWithdraw()
        if (optionalClient.isPresent) {
            val client: Client = optionalClient.get()
            val clientBalance = calculateBalance(client.balance, form.withdrawValue)
            client.balance = clientBalance
            clientRepository.save(client)
            withdrawRepository.save(Withdraw(LocalDate.now(),form.withdrawValue,client.getId(),"SAQUE"))
            log.info("Saque realizado com sucesso")
            return WithdrawPojo(true, WithdrawConstant.EFETUADO)
        } else {
            throw StandardException(WithdrawConstant.WITHDRAW, WithdrawConstant.RECUSADO, HttpStatus.BAD_REQUEST)
        }
    }

    fun calculateBalance(actualBalance: BigDecimal, withdrawValue: BigDecimal): BigDecimal {
        val clientBalance = (actualBalance - withdrawValue)
        if(clientBalance < BigDecimal.ZERO){
            throw StandardException(WithdrawConstant.WITHDRAW, WithdrawConstant.RECUSADO, HttpStatus.BAD_REQUEST)
        }
        return clientBalance
    }

}