package br.com.senac.flashfood.service.impl

import br.com.senac.flashfood.model.dto.purchase.PurchaseRequestDTO
import br.com.senac.flashfood.model.entity.Product
import br.com.senac.flashfood.model.entity.Purchase
import br.com.senac.flashfood.repository.ProductRepository
import br.com.senac.flashfood.repository.PurchaseRepository
import br.com.senac.flashfood.repository.RestaurantRepository
import br.com.senac.flashfood.repository.UserRepository
import br.com.senac.flashfood.service.PurchaseService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

@Service
class PurchaseServiceImpl : PurchaseService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var purchaseRepository: PurchaseRepository

    @Autowired
    private lateinit var mapper: ModelMapper

    @Transactional(rollbackFor = [Exception::class, RuntimeException::class])
    override fun save(requestDTO: PurchaseRequestDTO, username: String): UUID {
        println(requestDTO.toString())
        val USER = userRepository.findByEmail(username)!!
        val RESTAURANT = restaurantRepository.findById(requestDTO.restaurantId).get()
        var priceFinal = 0.0
        val PRODUCTS = ArrayList<Product>()
        requestDTO.products.forEach { prodUUID ->
            RESTAURANT.menu?.productsList?.forEach { prodInter ->
                if(prodUUID == prodInter.id) {
                    PRODUCTS.add(prodInter)
                    priceFinal += prodInter.price }
                }
        }
        val PURCHASE = purchaseRepository.save(Purchase(client = USER,
                        restaurant = RESTAURANT,
                        paymentType = requestDTO.paymentType,
                        totalValue = priceFinal,
                        products = PRODUCTS))

        return PURCHASE.codPurchase!!
    }


}