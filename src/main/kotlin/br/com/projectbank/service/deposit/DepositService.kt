package br.com.projectbank.service.deposit

import br.com.projectbank.domain.form.DepositForm
import br.com.projectbank.pojo.DepositPojo

interface DepositService {
    fun addDeposit(form: DepositForm): DepositPojo

}