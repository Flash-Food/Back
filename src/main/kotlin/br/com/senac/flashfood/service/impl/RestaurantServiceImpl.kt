package br.com.senac.flashfood.service.impl

import br.com.senac.flashfood.context.RolesContext
import br.com.senac.flashfood.model.dto.restaurant.RestaurantResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import br.com.senac.flashfood.model.entity.User
import br.com.senac.flashfood.repository.RestaurantRepository
import br.com.senac.flashfood.repository.UserRepository
import br.com.senac.flashfood.service.RestaurantService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class RestaurantServiceImpl : RestaurantService {

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var mapper: ModelMapper

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    override fun save(requestDTO: UserSignUpRequestDTO): UserSignUpResponseDTO {
        val user = mapper.map(requestDTO, User::class.java)
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.roles = arrayListOf(RolesContext.roleRestaurant)
        return mapper.map(userRepository.save(user), UserSignUpResponseDTO::class.java)
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun getAll() = restaurantRepository.findAll()
            .stream()
            .map { mapper.map(it, RestaurantResponseDTO::class.java) }
            .collect(Collectors.toList())

}