package br.com.projectbank.service.withdraw

import br.com.projectbank.domain.form.WithdrawForm
import br.com.projectbank.pojo.WithdrawPojo

interface WithdrawService {
    fun  withdraw(form: WithdrawForm) : WithdrawPojo
}