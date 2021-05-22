package br.com.senac.flashfood.service

import br.com.senac.flashfood.model.dto.restaurant.RestaurantResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO

interface RestaurantService {

    fun save(request: UserSignUpRequestDTO): UserSignUpResponseDTO

    fun update()

    fun getAll() : List<RestaurantResponseDTO>
}