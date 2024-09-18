package br.com.projectbank.service.transference

import br.com.projectbank.domain.form.TransferForm
import br.com.projectbank.pojo.TranferPojo

interface TransferService {
    fun transfer(form: TransferForm) : TranferPojo

}