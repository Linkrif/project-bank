package br.com.projectbank.domain.dto

import java.util.*

data class TransferDto (
    private val idTransacao : Long,
    var codigoConta : String,
    val cpf : String,
    val nome: String,
    val password : String,
    val dataNascimento : Date,
    val email : String,
    val saldo : Double
)