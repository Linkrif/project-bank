package br.com.projectbank.repository

import br.com.projectbank.domain.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository : JpaRepository<Client, Long>
{
    override fun findById(id : Long) : Optional<Client>
}