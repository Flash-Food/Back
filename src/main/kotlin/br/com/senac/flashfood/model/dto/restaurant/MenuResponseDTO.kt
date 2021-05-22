package br.com.senac.flashfood.model.dto.restaurant

import br.com.senac.flashfood.annotations.NoArgs
import java.util.*

@NoArgs
data class MenuResponseDTO (
    var id: UUID,
    var productsList : List<ProductResponseDTO>
)