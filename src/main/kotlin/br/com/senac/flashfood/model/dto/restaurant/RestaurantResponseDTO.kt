package br.com.senac.flashfood.model.dto.restaurant

import br.com.senac.flashfood.annotations.NoArgs
import java.util.*

@NoArgs
data class RestaurantResponseDTO (
        var id: UUID,
        var name: String,
        var phoneNumber: String,
        var description: String,
        var menu: MenuResponseDTO?
)