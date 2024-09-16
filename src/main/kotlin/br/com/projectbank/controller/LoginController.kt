package br.com.projectbank.controller

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.form.LoginForm
import br.com.projectbank.service.login.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/login")
class LoginController {

    @Autowired
    lateinit var loginService: LoginService

    @PostMapping
    fun login(@RequestBody form: @Valid LoginForm): ResponseEntity<UserAuthDto> {
        return ResponseEntity.ok(loginService.authUser(form))
    }


}