package br.com.projectbank.repository

import br.com.projectbank.domain.entity.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Long>
{

}