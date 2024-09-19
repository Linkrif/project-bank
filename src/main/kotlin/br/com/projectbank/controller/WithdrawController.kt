package br.com.projectbank.controller

import br.com.projectbank.constants.ConstantDataManager
import br.com.projectbank.domain.form.WithdrawForm
import br.com.projectbank.pojo.WithdrawPojo
import br.com.projectbank.service.withdraw.WithdrawService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Description
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ConstantDataManager.ENDPOINT_V1)
class WithdrawController {
    @Autowired
    lateinit var withdrawService : WithdrawService
    @Operation(summary = "Saque de valores disponiveis na conta.")
    @PutMapping("/withdraw")
    @Description("Endpoint para sacar valor da conta.")
    fun withdraw(@Validated @RequestBody form :  WithdrawForm) : ResponseEntity<WithdrawPojo> {
        return ResponseEntity.status(HttpStatus.OK).body(withdrawService.withdraw(form))
    }


}