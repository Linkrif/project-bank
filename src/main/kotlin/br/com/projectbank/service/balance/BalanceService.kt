package br.com.projectbank.service.balance

import br.com.projectbank.domain.form.BalanceForm
import br.com.projectbank.pojo.BalancePojo

interface BalanceService {
    fun addBalance(form: BalanceForm): BalancePojo

}