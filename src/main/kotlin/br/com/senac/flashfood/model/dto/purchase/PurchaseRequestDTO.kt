package br.com.senac.flashfood.model.dto.purchase

import br.com.senac.flashfood.constant.PaymentTypeConstants
import br.com.senac.flashfood.model.entity.Product
import lombok.Getter
import lombok.Setter
import lombok.ToString
import java.util.*
import javax.validation.constraints.NotBlank

@ToString
@Getter
@Setter
data class PurchaseRequestDTO (

        @NotBlank
        var restaurantId:   UUID,

        @NotBlank
        var paymentType:    PaymentTypeConstants,

        @NotBlank
        var products:       List<UUID>
)