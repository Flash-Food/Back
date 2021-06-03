package br.com.senac.flashfood.service

import br.com.senac.flashfood.model.dto.purchase.PurchaseRequestDTO
import java.util.*


interface PurchaseService {

    fun save(requestDTO: PurchaseRequestDTO, username: String) : UUID

}