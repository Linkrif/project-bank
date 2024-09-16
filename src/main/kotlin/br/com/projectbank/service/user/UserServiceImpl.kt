package br.com.projectbank.service.user

import br.com.projectbank.domain.entity.User
import br.com.projectbank.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    lateinit var userRepository : UserRepository

    fun save(user: User): User {
        return userRepository.save(user);
    }

}