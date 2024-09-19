package br.com.projectbank.domain.form

import br.com.projectbank.constants.DepositConstant
import br.com.projectbank.exception.StandardException
import org.hibernate.validator.constraints.NotBlank
import org.springframework.http.HttpStatus
import java.math.BigDecimal

class WithdrawForm (
    val withdrawValue : BigDecimal
) {
    fun validateWithdraw() {
        if (withdrawValue <= BigDecimal.ZERO)
            throw StandardException("WITHDRAW", DepositConstant.RECUSADO, HttpStatus.BAD_REQUEST)
    }
}