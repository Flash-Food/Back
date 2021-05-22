package br.com.senac.flashfood.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.HandlerExceptionResolver
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class FilterChainExceptionHandler : OncePerRequestFilter() {


    @Qualifier("handlerExceptionResolver")
    @Autowired
    private lateinit var resolver: HandlerExceptionResolver

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            chain.doFilter(request, response)
        } catch (e: Exception) {
            e.printStackTrace()
            resolver.resolveException(request, response, null, e)
        }
    }
}