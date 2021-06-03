package br.com.senac.flashfood.service

import br.com.senac.flashfood.model.dto.purchase.PurchaseResponseDTO
import br.com.senac.flashfood.model.dto.user.UserFindRequestDTO
import br.com.senac.flashfood.model.dto.user.UserFindResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import br.com.senac.flashfood.model.entity.User

interface UserService {

    fun save(request : UserSignUpRequestDTO) : UserSignUpResponseDTO

    fun findBy(username : String) : UserFindResponseDTO

    fun getPurchases(username: String) : List<PurchaseResponseDTO>
}