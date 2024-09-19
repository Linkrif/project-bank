package br.com.projectbank.controller

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.form.LoginForm
import br.com.projectbank.service.login.LoginService
import br.com.projectbank.constants.ConstantDataManager
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Description
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ConstantDataManager.ENDPOINT_V1)

class LoginController {

    @Autowired
    lateinit var loginService: LoginService

    @Operation(summary = "No username usar o CPF para acesso, e a sua senha de acesso.")

    @PostMapping("/login")
    @Description("Endpoint para login.")
    fun login(@Validated @RequestBody form: LoginForm): ResponseEntity<UserAuthDto> {
        return ResponseEntity.ok(loginService.authUser(form))
    }


}