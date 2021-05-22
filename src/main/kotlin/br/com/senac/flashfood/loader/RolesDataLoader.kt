package br.com.senac.flashfood.loader

import br.com.senac.flashfood.context.RolesContext
import br.com.senac.flashfood.model.entity.Privilege
import br.com.senac.flashfood.model.entity.Role
import br.com.senac.flashfood.model.entity.User
import br.com.senac.flashfood.repository.PrivilegeRepository
import br.com.senac.flashfood.repository.RoleRepository
import br.com.senac.flashfood.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.Ordered
import org.springframework.scheduling.annotation.Async
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel


@Component
class RolesDataLoader : ApplicationListener<ContextRefreshedEvent>, Ordered {

    var finish = false

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @Autowired
    private lateinit var privilegeRepository: PrivilegeRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if(finish) return

        val privileges = arrayListOf(createPrivilegeIfNotFound("READ_PRIVILEGE"),
        createPrivilegeIfNotFound("WRITE_PRIVILEGE"))
        RolesContext.roleAdmin = createRoleIfNotFound("ROLE_ADMIN", privileges)
        RolesContext.roleUser  = createRoleIfNotFound("ROLE_USER", privileges)
        RolesContext.roleRestaurant = createRoleIfNotFound("ROLE_RESTAURANT", privileges)

        finish = true
    }

    private fun createPrivilegeIfNotFound(name: String) = privilegeRepository.findByName(name)
            ?: privilegeRepository.save(Privilege(name = name))

    private fun createRoleIfNotFound(name: String, privileges: Collection<Privilege>) = roleRepository.findByName(name)
            ?: roleRepository.save(Role(name = name, privileges = privileges))

    override fun getOrder() = 25

}