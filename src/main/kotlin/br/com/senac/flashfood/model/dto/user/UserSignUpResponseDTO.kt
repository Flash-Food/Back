package br.com.senac.flashfood.model.dto.user

import br.com.senac.flashfood.annotations.NoArgs
import java.util.*

@NoArgs
data class UserSignUpResponseDTO (

        var id          : UUID,

        var name        : String,

        var email       : String,

        var cpf         : String,

        var phoneNumber  : String,

        var password     : String
)