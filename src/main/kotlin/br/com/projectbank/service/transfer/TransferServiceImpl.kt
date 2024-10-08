package br.com.projectbank.service.transfer

import br.com.projectbank.constants.TransferConstant
import br.com.projectbank.domain.entity.Client
import br.com.projectbank.domain.entity.Transfer
import br.com.projectbank.domain.form.TransferForm
import br.com.projectbank.exception.StandardException
import br.com.projectbank.pojo.TransferPojo
import br.com.projectbank.repository.ClientRepository
import br.com.projectbank.repository.TransferRepository
import br.com.projectbank.repository.UserRepository
import br.com.projectbank.utils.security.PrincipalUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate


@Service
class TransferServiceImpl : TransferService {
    @Autowired
    private lateinit var clientRepository: ClientRepository
    @Autowired
    private lateinit var transferRepository: TransferRepository
    @Autowired
    private lateinit var userRepository: UserRepository
    val log: Logger = LoggerFactory.getLogger(this::class.java)
    override fun transfer(form: TransferForm) : TransferPojo {
        val optionalClient = clientRepository.findById(PrincipalUtils.getId())
        form.validateTransference()
        if (optionalClient.isPresent) {
            val optionalClientTo = clientRepository.findByCpf(form.clientTo)

            val client: Client = optionalClient.get()
            val clientBalance = calculateBalance(client.balance, form.transferValue)
            client.balance = clientBalance
            clientRepository.save(client)

            val clientTo: Client = optionalClientTo.get()
            /*Valida se o usuario que vai receber está bloqueado e presente no objeto optionalClient, antes de fazer a transferencia*/
            if(optionalClientTo.isPresent && !userRepository.userIsBlocked(form.clientTo)){
                clientTo.balance += form.transferValue
                clientRepository.save(clientTo)
                transferRepository.save(Transfer(LocalDate.now(),form.transferValue,PrincipalUtils.getId(),clientTo.getId(),form.transferType))
                log.info("Transferencia executada com sucesso!")
            } else {
                throw StandardException(TransferConstant.TRANSFERENCE, TransferConstant.RECUSADO, HttpStatus.BAD_REQUEST)
            }

            return TransferPojo(true, TransferConstant.EFETUADO)
        } else {
            throw StandardException(TransferConstant.TRANSFERENCE, TransferConstant.RECUSADO, HttpStatus.BAD_REQUEST)
        }
    }

    fun calculateBalance(actualBalance: BigDecimal, transferenceBalance: BigDecimal): BigDecimal {
        val clientBalance = (actualBalance - transferenceBalance)
        if(clientBalance < BigDecimal.ZERO){
            throw StandardException(TransferConstant.TRANSFERENCE, TransferConstant.RECUSADO, HttpStatus.BAD_REQUEST)
        }
        return clientBalance
    }

}