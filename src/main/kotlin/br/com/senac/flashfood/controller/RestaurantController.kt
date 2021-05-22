package br.com.senac.flashfood.controller

import br.com.senac.flashfood.model.dto.restaurant.RestaurantResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import org.springframework.http.ResponseEntity

interface RestaurantController {

    fun signup(userDTO: UserSignUpRequestDTO): ResponseEntity<UserSignUpResponseDTO>

    fun getAll(): ResponseEntity<List<RestaurantResponseDTO>>

}