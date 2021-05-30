package br.com.senac.flashfood.controller

import br.com.senac.flashfood.model.dto.restaurant.RestaurantResponseDTO
import br.com.senac.flashfood.model.dto.restaurant.RestaurantWithMenuResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import org.springframework.http.ResponseEntity
import java.util.*

interface RestaurantController {

    fun signup(userDTO: UserSignUpRequestDTO): ResponseEntity<UserSignUpResponseDTO>

    fun getAll(): ResponseEntity<List<RestaurantResponseDTO>>

    fun getById(id: UUID): ResponseEntity<RestaurantWithMenuResponseDTO>

}