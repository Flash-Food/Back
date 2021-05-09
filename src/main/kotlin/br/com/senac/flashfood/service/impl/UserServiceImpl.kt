package br.com.senac.flashfood.service.impl

import br.com.senac.flashfood.model.entity.User
import br.com.senac.flashfood.repository.UserRepository
import br.com.senac.flashfood.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun save(user: User) : User {
       var user = userRepository.save(user)
        println(user.cpf)
        return user
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun updatePassword() {
        TODO("Not yet implemented")
    }

    override fun createOrder() {
        TODO("Not yet implemented")
    }
}