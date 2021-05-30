package br.com.senac.flashfood.loader

import br.com.senac.flashfood.model.entity.Menu
import br.com.senac.flashfood.model.entity.Restaurant
import br.com.senac.flashfood.repository.MenuRepository
import br.com.senac.flashfood.repository.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MenusDataLoader {

    @Autowired
    private lateinit var menuRepository: MenuRepository

    fun createMenuForRestaurant(restaurant: Restaurant) = saveMenu(Menu(restaurant = restaurant))

    private fun saveMenu(menu: Menu) = menuRepository.save(menu)

}