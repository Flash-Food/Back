package br.com.senac.flashfood.model.internal

import br.com.senac.flashfood.model.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserDetailsImpl(private val user: User) : UserDetails {

    override fun getAuthorities() = user.roles
            .stream()
            .map{r -> SimpleGrantedAuthority(r.name)}
            .collect(Collectors.toSet())

    override fun isEnabled() = true

    override fun getUsername() = user.email

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = user.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}