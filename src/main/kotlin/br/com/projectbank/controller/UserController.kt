package br.com.projectbank.controller


import br.com.projectbank.service.user.UserService
import br.com.projectbank.constants.ConstantDataManager
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ConstantDataManager.ENDPOINT_V1 + ConstantDataManager.ENDPOINT_USER)
class UserController(
    private val userService : UserService

) {



}