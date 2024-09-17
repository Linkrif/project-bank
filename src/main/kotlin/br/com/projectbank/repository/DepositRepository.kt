package br.com.projectbank.repository

import br.com.projectbank.domain.entity.Deposit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepositRepository : JpaRepository<Deposit, Long> {
}