package br.com.projectbank.controller

import br.com.projectbank.domain.form.DepositForm
import br.com.projectbank.pojo.DepositPojo
import br.com.projectbank.service.deposit.DepositService
import br.com.projectbank.constants.ConstantDataManager
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
class DepositController {

    @Autowired
    lateinit var depositService : DepositService

    @PutMapping("/addDeposit")
    fun addDeposit(@RequestBody form : @Valid DepositForm) : ResponseEntity<DepositPojo>{
        return ResponseEntity.status(HttpStatus.CREATED).body(depositService.addDeposit(form))

    }


}