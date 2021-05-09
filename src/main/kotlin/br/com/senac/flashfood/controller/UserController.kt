package br.com.senac.flashfood.controller

import br.com.senac.flashfood.model.dto.user.UserSignUpRequestDTO
import br.com.senac.flashfood.model.dto.user.UserSignUpResponseDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import java.util.*
import javax.validation.Valid

interface UserController {

    fun signUp(userDTO : UserSignUpRequestDTO)  : ResponseEntity<UserSignUpResponseDTO>

    fun login(user : Objects)                                       : ResponseEntity<String>

    fun forgotItPassword(email : String)                            : ResponseEntity<String>

    fun alterPassword(email : String, OldPassword : String, NewPassword : String) : ResponseEntity<String>

    fun order (list : List<String>) : ResponseEntity<String>
}