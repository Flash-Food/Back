package br.com.senac.flashfood.controller

import br.com.senac.flashfood.model.dto.purchase.PurchaseRequestDTO
import org.springframework.http.ResponseEntity
import java.util.*

interface PurchaseController {

    fun save(requestDTO: PurchaseRequestDTO): ResponseEntity<UUID>

}