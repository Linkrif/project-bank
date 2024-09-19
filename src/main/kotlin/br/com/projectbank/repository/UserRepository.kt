package br.com.projectbank.repository

import br.com.projectbank.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>
{
    fun findByUsername(username : String) : User
    fun existsByUsername(username : String) : Boolean?
    @Query("SELECT u.blocked from tb_users u where username = ?1")
    fun userIsBlocked(username : String) : Boolean

}


