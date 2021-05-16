package br.com.senac.flashfood.filter

import br.com.senac.flashfood.constant.JWTConstants
import br.com.senac.flashfood.util.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.filter.GenericFilterBean
import javax.naming.AuthenticationException
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter : GenericFilterBean {

    private var jwtUtil: JwtUtil

    private var userDetailService: UserDetailsService

    constructor(authenticationManager: AuthenticationManager, jwtUtil: JwtUtil, userDetailService: UserDetailsService) : super(){
        this.jwtUtil = jwtUtil
        this.userDetailService = userDetailService
    }

    private fun getAuthentication(authorizationHeader: String?): UsernamePasswordAuthenticationToken {
        val token = authorizationHeader?.substring(7) ?: ""
        if(jwtUtil.isTokenValid(token)) {
            val username = jwtUtil.getUserName(token)
            val user = userDetailService.loadUserByUsername(username)
            return UsernamePasswordAuthenticationToken(user, user.isCredentialsNonExpired, user.authorities)
        }
        throw UsernameNotFoundException("Auth invalid!")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val HTTP_REQ = request as HttpServletRequest
        val authorizationHeader = HTTP_REQ.getHeader(JWTConstants.HEADER_NAME.getValue())
        if(authorizationHeader != null && authorizationHeader.startsWith(JWTConstants.PREFIX.getValue())) {
            val auth = getAuthentication(authorizationHeader)
            SecurityContextHolder.getContext().authentication = auth
            chain?.doFilter(request, response)
        }
        throw AuthenticationException()
    }
}
