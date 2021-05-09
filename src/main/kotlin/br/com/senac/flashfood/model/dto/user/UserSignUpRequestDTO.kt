package br.com.senac.flashfood.model.dto.user

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class UserSignUpRequestDTO (
        @NotBlank
        var name    : String,

        @NotBlank
        @Pattern(regexp = "^[a-z0-9]+@[a-z0-9]+\\.[a-z0-9]+\\.[a-z0-9]+?$")
        var email       : String,

        @NotBlank
        var phoneNumber : String,

        @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$")
        var cpf         : String,

        @NotBlank
        var password    :String
)
