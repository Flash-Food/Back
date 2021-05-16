package br.com.senac.flashfood.model.dto.user

import br.com.senac.flashfood.annotations.NoArgs

@NoArgs
data class UserFindResponseDTO (
    var name: String,
    var email: String,
    var cpf: String,
    var phoneNumber: String
)