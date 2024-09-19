package br.com.projectbank.domain.entity

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "tb_deposit_history")
class Deposit (
    @Column(name = "deposit_date", nullable = false)
    var depositDate : LocalDate,
    @Column(name = "deposit_value", nullable = false, precision=10, scale=2)
    val depositValue : BigDecimal,
    @Column(name = "client_id", nullable = false)
    val clientId : Long
) : BaseEntity()
