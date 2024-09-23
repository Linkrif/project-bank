package br.com.projectbank.controller

import br.com.projectbank.domain.form.BalanceForm
import br.com.projectbank.pojo.BalancePojo
import br.com.projectbank.service.balance.BalanceService
import br.com.projectbank.constants.ConstantDataManager
import br.com.projectbank.exception.StandardException
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Description
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
    @Description("Endpoint para adicionar saldo a conta.")

    @Operation(summary = "Endpoint para adicionar saldo a conta.")
    fun addBalance(@RequestBody form : @Valid BalanceForm) : ResponseEntity<BalancePojo>{
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(balanceService.addBalance(form))
        }catch (e : Exception) {
            throw StandardException("Erro para adicionar saldo.","RECUSADO", HttpStatus.BAD_REQUEST)
        }

    }


}