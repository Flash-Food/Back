package br.com.senac.flashfood.service.impl

import br.com.senac.flashfood.context.RolesContext
import br.com.senac.flashfood.model.dto.restaurant.RestaurantResponseDTO
import br.com.senac.flashfood.model.dto.restaurant.RestaurantWithMenuResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import br.com.senac.flashfood.model.entity.Restaurant
import br.com.senac.flashfood.model.entity.User
import br.com.senac.flashfood.repository.ProductRepository
import br.com.senac.flashfood.repository.RestaurantRepository
import br.com.senac.flashfood.repository.UserRepository
import br.com.senac.flashfood.service.RestaurantService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import javax.persistence.Transient

@Service
class RestaurantServiceImpl : RestaurantService {

    @Transient @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    @Transient @Autowired
    private lateinit var productRepository: ProductRepository

    @Transient @Autowired
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

    override fun getAll() = restaurantRepository.findAll()
            .stream()
            .map { mapper.map(it, RestaurantResponseDTO::class.java) }
            .collect(Collectors.toList())

    override fun getById(id: UUID): RestaurantWithMenuResponseDTO {
        val RESTAURANT = restaurantRepository.findById(id).get()
        RESTAURANT.menu?.productsList
        return mapper.map(RESTAURANT, RestaurantWithMenuResponseDTO::class.java)
    }

    override fun getByProduct(id: UUID): RestaurantResponseDTO {
        var product = productRepository.findById(id).get()

        var restaurant = product.menu.restaurant

        return mapper.map(restaurant, RestaurantResponseDTO::class.java)
    }

}