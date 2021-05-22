package br.com.senac.flashfood.model.dto.restaurant

import br.com.senac.flashfood.annotations.NoArgs
import br.com.senac.flashfood.constant.CategoryProduct
import java.util.*

@NoArgs
data class ProductResponseDTO (
        var id: UUID,
        var name: String,
        var description: String,
        var price: Double,
        var category: CategoryProduct
)