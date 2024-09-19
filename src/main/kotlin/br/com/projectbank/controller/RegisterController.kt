package br.com.projectbank.controller

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.form.RegisterForm
import br.com.projectbank.service.register.RegisterService
import br.com.projectbank.constants.ConstantDataManager
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Description
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ConstantDataManager.ENDPOINT_V1)
class RegisterController {
    @Autowired
    lateinit var registerService : RegisterService
    @Description("Endpoint para registro.")
    @Operation(summary = "Registro de usuário, depois do registro, vai ser gerado um refresh token pra usar no authorize.")
    @PostMapping("/register")
    fun register(@Validated  @RequestBody form: RegisterForm): ResponseEntity<UserAuthDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(registerService.register(form))
    }
}