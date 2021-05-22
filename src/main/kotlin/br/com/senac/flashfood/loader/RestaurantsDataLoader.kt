package br.com.senac.flashfood.loader

import br.com.senac.flashfood.model.entity.Restaurant
import br.com.senac.flashfood.repository.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class RestaurantsDataLoader : ApplicationListener<ContextRefreshedEvent>, Ordered {

    private var finish = false

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    @Autowired
    private lateinit var menusDataLoader: MenusDataLoader

    @Autowired
    private lateinit var productsDataLoader: ProductsDataLoader

    @Autowired
    private lateinit var userRestaurantsDataLoader: UserRestaurantsDataLoader

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (finish) return
        createRestaurantIfNotFound(createBrotinos())
        finish = true
    }

    fun createBrotinos() = Restaurant(name = "Brotinos",
        phoneNumber = "11 96565-0012",
        description = "Restaurante frances, localizado na P3",
        user = userRestaurantsDataLoader.createUserBrotinos()
        )

    fun createRestaurantIfNotFound(restaurant: Restaurant) = restaurantRepository.findByName(restaurant.name)
            ?: productsDataLoader.createProductsForBrotinos(
                    menusDataLoader.createMenuForBrotinos(
                            saveRestaurant(restaurant)
                    )
            )


    fun saveRestaurant(restaurant: Restaurant) = restaurantRepository.save(restaurant)

    override fun getOrder() = 30
}