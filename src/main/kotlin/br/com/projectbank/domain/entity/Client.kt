package br.com.projectbank.domain.entity

import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Email


@Entity
@Table(name = "tb_client")
class Client(
    @Column(name = "account_code", nullable = false)
    var accountCode : String,
    @CPF
    @Column(name = "cpf", nullable = false)
    val cpf : String,
    @Column(name = "nome", nullable = false)
    val nome: String,
    @Column(name = "data_nascimento", nullable = false)
    val dataNascimento : LocalDate,
    @Email
    @Column(name = "email", nullable = true)
    val email : String,
    @Column(name = "balance", nullable = false, precision=10, scale=2)
    var balance : BigDecimal,
    @Column(name = "phoneNumber", nullable = true)
    var phoneNumber : String,
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User
) : BaseEntity()
{

}
