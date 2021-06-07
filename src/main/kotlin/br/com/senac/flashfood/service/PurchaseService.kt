package br.com.senac.flashfood.service

import br.com.senac.flashfood.model.dto.purchase.PurchaseRequestDTO
import br.com.senac.flashfood.model.dto.restaurant.ProductResponseDTO
import java.util.*


interface PurchaseService {

    fun save(requestDTO: PurchaseRequestDTO, username: String) : UUID

    fun getProducts(cod: UUID): List<ProductResponseDTO>

}