package br.com.senac.flashfood.service

import br.com.senac.flashfood.model.dto.restaurant.ProductResponseDTO
import java.util.*

interface ProductService {

    fun findByName(name: String): List<ProductResponseDTO>

}