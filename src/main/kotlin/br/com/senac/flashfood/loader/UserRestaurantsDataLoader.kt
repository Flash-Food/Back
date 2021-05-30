package br.com.senac.flashfood.loader

import br.com.senac.flashfood.context.RolesContext
import br.com.senac.flashfood.model.entity.Role
import br.com.senac.flashfood.model.entity.User
import br.com.senac.flashfood.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserRestaurantsDataLoader {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    fun createUserBrotinos() = createUserIfNotFound(userBrotinos())

    fun createUserVictorinos() = createUserIfNotFound(userVictorinos())

    fun createUserDogaoHouse() = createUserIfNotFound(userDogaoHouse())

    fun userBrotinos() = User(
                name = "Brotinos_User",
                email = "brotinos@gmail.com",
                cpf = "432.332.11-09",
                phoneNumber = "11 5656-8987",
                password = bCryptPasswordEncoder.encode("123"),
                roles = arrayListOf(RolesContext.roleRestaurant)
        )

    fun userVictorinos() = User(
            name = "Victorinos_User",
            email = "victorinos@gmail.com",
            cpf = "132.312.21-19",
            phoneNumber = "11 5126-8181",
            password = bCryptPasswordEncoder.encode("123"),
            roles = arrayListOf(RolesContext.roleRestaurant)
    )

    fun userDogaoHouse() = User(
            name = "DogaoHouse_User",
            email = "dogaohouse@gmail.com",
            cpf = "137.512.11-29",
            phoneNumber = "11 5921-1111",
            password = bCryptPasswordEncoder.encode("123"),
            roles = arrayListOf(RolesContext.roleRestaurant)
    )



    fun createUserIfNotFound(user: User) = userRepository.findByEmail(user.email)
            ?: saveUser(user)

    fun saveUser(user: User) = userRepository.save(user)

    fun deleteByUsername(email: String) = userRepository.findByEmail(email)?.let {
        userRepository.deleteById(it.id!!)
    }
}