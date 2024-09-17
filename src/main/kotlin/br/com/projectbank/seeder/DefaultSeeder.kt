package br.com.projectbank.seeder

import br.com.projectbank.domain.entity.User
import br.com.projectbank.domain.enums.RoleEnum
import br.com.projectbank.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class DefaultSeeder() {
    lateinit var seedAdminUsername: String

    lateinit var seedAdminPassword: String

    lateinit var userRepository: UserRepository

    lateinit var passwordEncoder: PasswordEncoder
    fun DefaultSeeder( @Value("\${seed_admin_username}")  seedAdminUsername: String,
                       @Value("\${seed_admin_password}") seedAdminPassword: String,
                       userRepository: UserRepository,
                       passwordEncoder: PasswordEncoder) {
        this.seedAdminUsername = seedAdminUsername;
        this.seedAdminPassword = seedAdminPassword;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    fun seedAdmin() {
        if (userRepository.count() == 0L) {
            log.info("CREATING ADMIN SEED")
            val admin = User(seedAdminUsername,passwordEncoder.encode(seedAdminPassword),"Admin User",(Collections.singleton(RoleEnum.ADMIN)),false,null)
            userRepository.save(admin)
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(DefaultSeeder::class.java)
    }
}