package br.com.senac.flashfood.repository

import br.com.senac.flashfood.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    fun findByEmailAndPassword(email: String, password: String) : User

    fun findByEmail(email: String?) : User
}