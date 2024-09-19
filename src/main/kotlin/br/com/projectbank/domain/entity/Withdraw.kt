package br.com.projectbank.domain.entity

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "tb_withdraw_history")
class Withdraw (
    @Column(name = "withdraw_date", nullable = false)
    var withDrawDate : LocalDate,
    @Column(name = "withdraw_value", nullable = false, precision=10, scale=2)
    val withDrawValue : BigDecimal,
    @Column(name = "by_client_id", nullable = false)
    val byClientId : Long,
    @Column(name = "type", nullable = false)
    val type : String
    ) : BaseEntity()