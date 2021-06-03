package br.com.senac.flashfood.service.impl

import br.com.senac.flashfood.context.RolesContext
import br.com.senac.flashfood.model.dto.purchase.PurchaseResponseDTO
import br.com.senac.flashfood.model.dto.user.UserFindRequestDTO
import br.com.senac.flashfood.model.dto.user.UserFindResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import br.com.senac.flashfood.model.entity.User
import br.com.senac.flashfood.repository.UserRepository
import br.com.senac.flashfood.service.UserService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    private lateinit var mapper: ModelMapper

    override fun save(request: UserSignUpRequestDTO) : UserSignUpResponseDTO {
        val user = mapper.map(request, User::class.java)
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.roles = arrayListOf(RolesContext.roleUser)
        return mapper.map(userRepository.save(user), UserSignUpResponseDTO::class.java)
    }

    override fun findBy(username: String): UserFindResponseDTO {
       val user = userRepository.findByEmail(username)
       return mapper.map(user, UserFindResponseDTO::class.java)
    }

    override fun getPurchases(username: String): List<PurchaseResponseDTO> {
        val USER = userRepository.findByEmail(username)

        val PURCHASES = USER?.purchaseList ?: return ArrayList()

        return PURCHASES.map {
            mapper.map(it, PurchaseResponseDTO::class.java)
        }
    }

}