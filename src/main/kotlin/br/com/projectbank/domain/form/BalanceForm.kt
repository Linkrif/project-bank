package br.com.projectbank.domain.form

import br.com.projectbank.constants.DepositConstant
import br.com.projectbank.exception.StandardException
import org.springframework.http.HttpStatus
import java.math.BigDecimal
import org.hibernate.validator.constraints.NotBlank
class BalanceForm (

    @field:NotBlank(message = "field 'deposit' is null or blank")
    val deposit : BigDecimal
)

{
    fun validateDeposit() {
        if(deposit <= BigDecimal.ZERO)
            throw StandardException("DEPOSIT", DepositConstant.RECUSADO, HttpStatus.BAD_REQUEST)
    }
}