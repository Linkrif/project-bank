package br.com.projectbank.repository

import br.com.projectbank.domain.entity.Withdraw
import org.springframework.data.jpa.repository.JpaRepository

interface WithdrawRepository: JpaRepository<Withdraw, Long> {
}