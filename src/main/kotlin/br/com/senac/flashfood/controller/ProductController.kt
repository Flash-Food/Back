package br.com.senac.flashfood.controller

import br.com.senac.flashfood.model.dto.restaurant.ProductResponseDTO
import org.springframework.http.ResponseEntity

interface ProductController {

    fun findByName(name: String): ResponseEntity<List<ProductResponseDTO>>

}