package br.com.senac.flashfood.loader

import br.com.senac.flashfood.constant.PaymentTypeConstants
import br.com.senac.flashfood.model.entity.Product
import br.com.senac.flashfood.model.entity.Purchase
import br.com.senac.flashfood.repository.ProductRepository
import br.com.senac.flashfood.repository.PurchaseRepository
import br.com.senac.flashfood.repository.RestaurantRepository
import br.com.senac.flashfood.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class PurchaseDataLoader: ApplicationListener<ContextRefreshedEvent>, Ordered {

    private var finsih = true

    @Autowired
    private lateinit var purchaseRepository: PurchaseRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    @Autowired
    private lateinit var productRepository: ProductRepository


    override fun onApplicationEvent(p0: ContextRefreshedEvent) {

        if(finsih) return
        val user = userRepository.findByEmail("rafinha@gmail.com")

        val restaurant = restaurantRepository.findAll()[0]
        val products = ArrayList<Product>()
        restaurant.menu?.productsList?.forEach {
            products.add(it)
        }
        var valueFinal = 0.0
        products.forEach {
            valueFinal += it.price
        }

        val purchase = Purchase(
                client = user!!,
                restaurant = restaurant,
                paymentType = PaymentTypeConstants.CARD,
                products = products,
                totalValue = valueFinal
        )


        purchaseRepository.save(purchase)

    }

    override fun getOrder() = 35
}