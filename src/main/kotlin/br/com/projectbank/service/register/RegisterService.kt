package br.com.projectbank.service.register

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.form.RegisterForm

interface RegisterService {
    fun register(form: RegisterForm): UserAuthDto
}