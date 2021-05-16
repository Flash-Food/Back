package br.com.senac.flashfood.controller

import br.com.senac.flashfood.model.dto.user.UserFindRequestDTO
import br.com.senac.flashfood.model.dto.user.UserFindResponseDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import org.springframework.http.ResponseEntity

interface UserController {

    fun signUp(userDTO : UserSignUpRequestDTO)  : ResponseEntity<UserSignUpResponseDTO>

    fun login()                                 : ResponseEntity<Void>

    fun findUser()                              : ResponseEntity<UserFindResponseDTO>

    fun forgotItPassword(email : String)                            : ResponseEntity<String>

    fun alterPassword(email : String, OldPassword : String, NewPassword : String) : ResponseEntity<String>

    fun order (list : List<String>) : ResponseEntity<String>
}