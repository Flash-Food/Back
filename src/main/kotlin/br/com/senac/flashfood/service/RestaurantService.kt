package br.com.senac.flashfood.service

import br.com.senac.flashfood.model.dto.restaurant.RestaurantResponseDTO
import br.com.senac.flashfood.model.dto.restaurant.RestaurantWithMenuResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import java.util.*

interface RestaurantService {

    fun save(request: UserSignUpRequestDTO): UserSignUpResponseDTO

    fun getAll() : List<RestaurantResponseDTO>

    fun getById(id: UUID) : RestaurantWithMenuResponseDTO

    fun getByProduct(id: UUID): RestaurantResponseDTO
}