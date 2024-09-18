package br.com.projectbank.domain.entity

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "tb_transfer_history")
class Transfer (
    @Column(name = "transfer_date", nullable = false)
    var transferDate : LocalDate,
    @Column(name = "transfer_value", nullable = false, precision=10, scale=2)
    val transferValue : BigDecimal,
    @Column(name = "by_client_id", nullable = false)
    val byClientId : Long,
    @Column(name = "to_client_id", nullable = false)
    val toClientId : Long,
    @Column(name = "type", nullable = false)
    val type : String
) : BaseEntity()
