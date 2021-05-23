package br.com.senac.flashfood.filter

import br.com.senac.flashfood.constant.JWTConstants
import br.com.senac.flashfood.model.internal.Credentials
import br.com.senac.flashfood.service.UserService
import br.com.senac.flashfood.service.impl.UserServiceImpl
import br.com.senac.flashfood.util.JwtUtil
import br.com.senac.flashfood.util.UserUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter: AbstractAuthenticationProcessingFilter {

    private val jwtUtil : JwtUtil

    private val userUtils: UserUtils

    constructor(url: String, auth: AuthenticationManager, jwtUtil: JwtUtil, userUtils: UserUtils) : super(url) {
        authenticationManager = auth
        this.jwtUtil = jwtUtil
        this.userUtils = userUtils
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val credentials = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(credentials.username, credentials.password)
        return authenticationManager.authenticate(token)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        authResult?.let {
            var token = jwtUtil.generateToken(it.name, it.authorities.toList())
            token = "${JWTConstants.PREFIX.getValue()} $token"
            response?.addHeader(JWTConstants.HEADER_NAME.getValue(), token)
            userUtils.updateFinalAccess(it.name)
        }
        chain?.doFilter(request, response)
    }

    }