package br.com.projectbank.domain.form

import br.com.projectbank.constants.DepositConstant
import br.com.projectbank.exception.StandardException
import org.hibernate.validator.constraints.NotBlank
import org.springframework.http.HttpStatus
import java.math.BigDecimal


class TransferForm (
    @NotBlank(message = "Campo: 'transferValue' null or blank")
    val transferValue : BigDecimal,
    @NotBlank(message = "Campo: 'transferType' null or blank")
    val transferType : String,
    @NotBlank(message = "Campo: 'clientTo' null or blank")
    val clientTo : String
)

{
    fun validateTransference() {
        if(transferValue <= BigDecimal.ZERO)
            throw StandardException("DEPOSIT", DepositConstant.RECUSADO, HttpStatus.BAD_REQUEST)
    }
}