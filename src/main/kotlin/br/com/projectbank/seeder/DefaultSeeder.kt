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
    private var seedAdminUsername: String? = null

    private var seedAdminPassword: String? = null

    private var userRepository: UserRepository? = null

    private var passwordEncoder: PasswordEncoder? = null
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
        if (userRepository?.count() == 0L) {
            log.info("CREATING ADMIN SEED")
            val admin = User()

            admin.username = (seedAdminUsername)
            admin.password = (passwordEncoder?.encode(seedAdminPassword))
            admin.name = ("Default Admin")
            admin.roles =(Collections.singleton(RoleEnum.ADMIN))

            userRepository?.save(admin)
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(DefaultSeeder::class.java)
    }
}