package br.com.projectbank.service.login

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.enums.RoleEnum
import br.com.projectbank.domain.form.LoginForm
import br.com.projectbank.domain.form.RegisterForm


interface LoginService {
    fun authUser(form: LoginForm): UserAuthDto


}