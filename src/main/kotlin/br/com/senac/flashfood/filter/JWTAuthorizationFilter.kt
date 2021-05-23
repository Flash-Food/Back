package br.com.senac.flashfood.filter

import br.com.senac.flashfood.constant.JWTConstants
import br.com.senac.flashfood.service.UserService
import br.com.senac.flashfood.service.impl.UserServiceImpl
import br.com.senac.flashfood.util.JwtUtil
import br.com.senac.flashfood.util.UserUtils
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.SpringSessionContext
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.filter.GenericFilterBean
import java.util.regex.Pattern
import javax.naming.AuthenticationException
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.xml.transform.sax.SAXSource


class JWTAuthorizationFilter : GenericFilterBean {

    private val REGEX =  "/user/login|/user/signup|/error".toRegex()

    private var jwtUtil: JwtUtil

    private var userDetailService: UserDetailsService

    private val userUtils: UserUtils

    constructor(jwtUtil: JwtUtil, userDetailService: UserDetailsService, userUtils: UserUtils) : super(){
        this.jwtUtil = jwtUtil
        this.userDetailService = userDetailService
        this.userUtils = userUtils
    }

    private fun getAuthentication(authorizationHeader: String?, response: ServletResponse?): UsernamePasswordAuthenticationToken {
        val token = authorizationHeader?.substring(7) ?: ""
        if(jwtUtil.isTokenValid(token)) {
            val username = jwtUtil.getUserName(token)
            val user = userDetailService.loadUserByUsername(username)

            if(jwtUtil.isTokenExpirated(token))
                if(jwtUtil.validTimeTolerance(token, userUtils.getFinalAccess(username)))
                    createNewToken(user.username, user.authorities, response)
                else
                    throw AuthenticationException("Token expirated")

            return UsernamePasswordAuthenticationToken(user, user.isCredentialsNonExpired, user.authorities)
        }
        throw UsernameNotFoundException("Auth invalid!")
    }

    fun createNewToken(username: String, authoritis: Collection<GrantedAuthority>, response: ServletResponse?) {
        println("CRINADO TOKEN")
        val response = response as HttpServletResponse
        var token = jwtUtil.generateToken(username, authoritis.toList())
        token = "${JWTConstants.PREFIX.getValue()} $token"
        response.addHeader(JWTConstants.HEADER_NAME.getValue(), token)
        userUtils.updateFinalAccess(username)
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val HTTP_REQ = request as HttpServletRequest
        if(!REGEX.matches(HTTP_REQ.servletPath)) {
            val authorizationHeader = HTTP_REQ.getHeader(JWTConstants.HEADER_NAME.getValue())
            if (authorizationHeader != null && authorizationHeader.startsWith(JWTConstants.PREFIX.getValue())) {
                val auth = getAuthentication(authorizationHeader, response)
                SecurityContextHolder.getContext().authentication = auth
            } else throw AuthenticationException()
        }
        chain?.doFilter(request, response)
    }

}
