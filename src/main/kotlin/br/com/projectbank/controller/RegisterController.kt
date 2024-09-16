package br.com.projectbank.controller

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.form.RegisterForm
import br.com.projectbank.service.register.RegisterService
import br.com.projectbank.utils.ConstantDataManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping(ConstantDataManager.ENDPOINT_V1)
class RegisterController {
    @Autowired
    lateinit var registerService : RegisterService
    @PostMapping("/register")
    fun register(@RequestBody form: @Valid RegisterForm): ResponseEntity<UserAuthDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(registerService.register(form))
    }
}