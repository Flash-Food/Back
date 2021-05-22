package br.com.senac.flashfood.service.impl

import br.com.senac.flashfood.repository.RestaurantRepository
import br.com.senac.flashfood.service.RestaurantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RestaurantServiceImpl : RestaurantService {

    @Autowired
    private lateinit var restaurantRepository: RestaurantRepository

    override fun save() {
        TODO("Not yet implemented")
    }

    override fun update() {
        TODO("Not yet implemented")
    }

}