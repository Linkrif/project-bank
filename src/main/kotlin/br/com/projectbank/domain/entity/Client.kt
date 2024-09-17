package br.com.projectbank.domain.entity

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*


@Entity
@Table(name = "tb_client")
class Client(
    @Column(name = "account_code", nullable = false)
    var accountCode : String,
    @Column(name = "cpf", nullable = false)
    val cpf : String,
    @Column(name = "nome", nullable = false)
    val nome: String,
    @Column(name = "dataNascimento", nullable = false)
    val dataNascimento : LocalDate,
    @Column(name = "email", nullable = false)
    val email : String,
    @Column(name = "balance", nullable = false)
    val balance : BigDecimal,
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User
) : BaseEntity()
