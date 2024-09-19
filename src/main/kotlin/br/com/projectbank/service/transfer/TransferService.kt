package br.com.projectbank.service.transfer

import br.com.projectbank.domain.form.TransferForm
import br.com.projectbank.pojo.TransferPojo

interface TransferService {
    fun transfer(form: TransferForm) : TransferPojo

}