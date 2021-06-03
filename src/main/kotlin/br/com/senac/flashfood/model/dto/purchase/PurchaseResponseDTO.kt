package br.com.senac.flashfood.model.dto.purchase

import br.com.senac.flashfood.annotations.NoArgs
import br.com.senac.flashfood.constant.PaymentTypeConstants
import br.com.senac.flashfood.model.dto.restaurant.ProductResponseDTO
import br.com.senac.flashfood.model.dto.restaurant.RestaurantResponseDTO
import java.util.*

@NoArgs
data class PurchaseResponseDTO (
        var purchaseCod: UUID,
        var restaurant: RestaurantResponseDTO,
        var purchaseDate: Date,
        var totalValue: Double,
        var paymentType: PaymentTypeConstants,
        var products:  List<ProductResponseDTO>
)