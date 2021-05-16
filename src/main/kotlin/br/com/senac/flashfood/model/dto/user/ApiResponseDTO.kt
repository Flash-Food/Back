package br.com.senac.flashfood.model.dto.user

import br.com.senac.flashfood.annotations.NoArgs

@NoArgs
data class ApiResponseDTO (var code: Int = 1, var msg: String = "AAAA")