package br.com.projectbank.domain.entity

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "tb_deposit_history")
class Deposit (
    @Column(name = "deposit_date", nullable = false)
    var depositDate : LocalDate,
    @Column(name = "deposit_value", nullable = false)
    val depositValue : BigDecimal,
    @Column(name = "client_id", nullable = false)
    val clientId : Long
) : BaseEntity()
