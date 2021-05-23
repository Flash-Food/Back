package br.com.senac.flashfood.util

import br.com.senac.flashfood.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserUtils {

    @Autowired
    lateinit var userRepository: UserRepository

    fun updateFinalAccess(username: String) {
        var user = userRepository.findByEmail(username)
        user?.dateAccess = Date(System.currentTimeMillis())
        if(user != null)
        userRepository.save(user)
    }

    fun getFinalAccess(username: String?): Date {
        return userRepository.findByEmail(username)?.dateAccess?:Date(System.currentTimeMillis())
    }
}