package br.com.projectbank.domain.dto

import java.time.LocalDate

data class UserDto(
    val id : Long,
    val accountCode : String,
    val cpf : String,
    val name: String,
    val birthDate : LocalDate,
    val email : String,
    val balance : Double
)



