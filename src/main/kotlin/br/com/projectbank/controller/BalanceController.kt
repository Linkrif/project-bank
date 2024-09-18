package br.com.projectbank.controller

import br.com.projectbank.domain.form.BalanceForm
import br.com.projectbank.pojo.BalancePojo
import br.com.projectbank.service.balance.BalanceService
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
class BalanceController {

    @Autowired
    lateinit var balanceService : BalanceService

    @PutMapping("/addBalance")
    fun addBalance(@RequestBody form : @Valid BalanceForm) : ResponseEntity<BalancePojo>{
        return ResponseEntity.status(HttpStatus.CREATED).body(balanceService.addBalance(form))
    }


}