package br.com.projectbank.repository

import br.com.projectbank.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>
{
    @Query(value = "Select top(1) 1 from tb_users where cpf=?1 and password = ?2", nativeQuery = true)
    fun loginValid(cpf : String, password : Int) : Boolean
    @Query(value = "Select top(1) * from tb_users_info where cpf=?1", nativeQuery = true)
    fun existsByCpf(cpf : String) : Boolean
    @Query(value = "Select top(1) * from tb_users_info where cpf=?1", nativeQuery = true)
    fun findByCpf(cpf : String) : User
}


