package br.com.senac.flashfood.repository

import br.com.senac.flashfood.model.entity.Privilege
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PrivilegeRepository : JpaRepository<Privilege, UUID> {
    fun findByName(name: String) : Privilege?
}