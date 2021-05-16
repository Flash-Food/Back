package br.com.senac.flashfood.service.impl

import br.com.senac.flashfood.model.internal.UserDetailsImpl
import br.com.senac.flashfood.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(email: String?) = UserDetailsImpl(userRepository.findByEmail(email))

}