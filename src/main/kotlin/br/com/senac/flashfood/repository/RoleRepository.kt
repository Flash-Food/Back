package br.com.senac.flashfood.repository

import br.com.senac.flashfood.model.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
interface RoleRepository : JpaRepository<Role, UUID> {


    fun findByName(name: String) : Role?

}