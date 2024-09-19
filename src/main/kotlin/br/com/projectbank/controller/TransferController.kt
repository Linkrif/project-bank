package br.com.projectbank.controller

import br.com.projectbank.constants.ConstantDataManager
import br.com.projectbank.domain.form.TransferForm
import br.com.projectbank.pojo.TransferPojo
import br.com.projectbank.service.transfer.TransferService
import io.swagger.v3.oas.annotations.Operation
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
    @Operation(summary = "Endpoint de transferencia de saldo entre contas.")
    fun transferBalance(@RequestBody form : @Valid TransferForm) : ResponseEntity<TransferPojo>{
        return ResponseEntity.status(HttpStatus.OK).body(transferService.transfer(form))
    }


}