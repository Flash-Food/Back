package br.com.senac.flashfood.model.internal

import br.com.senac.flashfood.annotations.NoArgs

@NoArgs
data class Credentials (
        var username: String,
        var password: String
)