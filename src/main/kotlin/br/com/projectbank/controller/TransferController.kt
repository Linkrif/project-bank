package br.com.projectbank.controller

import br.com.projectbank.constants.ConstantDataManager
import br.com.projectbank.domain.form.TransferForm
import br.com.projectbank.pojo.TranferPojo
import br.com.projectbank.service.transference.TransferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(ConstantDataManager.ENDPOINT_V1)
class TransferController {

    @Autowired
    lateinit var transferService : TransferService

    @PutMapping("/transferBalance")
    fun transferBalance(@RequestBody form : @Valid TransferForm) : ResponseEntity<TranferPojo>{
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.transfer(form))
    }


}