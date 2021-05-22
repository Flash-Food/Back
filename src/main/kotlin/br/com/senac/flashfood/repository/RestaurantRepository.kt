package br.com.senac.flashfood.repository

import br.com.senac.flashfood.model.entity.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RestaurantRepository : JpaRepository<Restaurant, UUID > {

    fun findByName(name: String) : Restaurant?

}