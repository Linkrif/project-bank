package br.com.projectbank.service.balance

import br.com.projectbank.constants.DepositConstant
import br.com.projectbank.domain.entity.Client
import br.com.projectbank.domain.entity.Deposit
import br.com.projectbank.domain.entity.User
import br.com.projectbank.domain.form.BalanceForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.pojo.BalancePojo
import br.com.projectbank.repository.ClientRepository
import br.com.projectbank.repository.DepositRepository
import br.com.projectbank.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class BalanceServiceImpl : BalanceService {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var clientRepository: ClientRepository
    @Autowired
    private lateinit var depositRepository: DepositRepository

    override fun addBalance(form: BalanceForm) : BalancePojo {
        val clientId : Long = (SecurityContextHolder.getContext().authentication.principal).toString().toLong()
        form.validateDeposit()
        val optionalUser = userRepository.findById(clientId)

        if (optionalUser.isPresent) {
            val user = optionalUser.get()
            user.client!!.balance = sumBalance(form.deposit, user.client!!.balance)
            clientRepository.save(user.client!!)
            depositRepository.save(Deposit(LocalDate.now(),form.deposit,clientId))
            return BalancePojo(true,DepositConstant.EFETUADO)
        } else {
            throw StandardException(DepositConstant.DEPOSIT, DepositConstant.RECUSADO, HttpStatus.BAD_REQUEST)
        }
    }



    fun sumBalance(valorAtual: BigDecimal, novoValor: BigDecimal): BigDecimal {
        return (valorAtual + novoValor);
    }

}