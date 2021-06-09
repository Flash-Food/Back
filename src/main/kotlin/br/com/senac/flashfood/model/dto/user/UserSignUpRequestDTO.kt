package br.com.senac.flashfood.model.dto.user

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class UserSignUpRequestDTO (
        @field:NotBlank
        var name    : String,


        @field:NotBlank
        @Pattern(regexp = "^[a-z0-9]+@[a-z0-9]+\\.[a-z0-9]+\\.[a-z0-9]+?$")
        var email       : String,

        @field:NotBlank
        var phoneNumber : String,

        @field:NotNull
        @field:Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$")
        var cpf         : String,

        @field:NotBlank
        var password    :String
)
