package br.com.senac.flashfood.repository

import br.com.senac.flashfood.model.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MenuRepository : JpaRepository<Menu, UUID> {
}