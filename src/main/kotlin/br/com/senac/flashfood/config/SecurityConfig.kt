package br.com.senac.flashfood.config

import br.com.senac.flashfood.filter.JWTAuthenticationFilter
import br.com.senac.flashfood.filter.JWTAuthorizationFilter
import br.com.senac.flashfood.handler.FilterChainExceptionHandler
import br.com.senac.flashfood.util.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.LogoutFilter


@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {


    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private lateinit var userDetailsService : UserDetailsService

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var filterChainExceptionHandler: FilterChainExceptionHandler

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .cors().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/user/login", "/user/signup").permitAll()
                    .antMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .antMatchers("/restaurant**").hasAnyAuthority("ROLE_USER", "ROLE_RESTAURANT", "ROLE_ADMIN")
                    .antMatchers("/restaurant/signup").hasAuthority("ROLE_ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(
                            filterChainExceptionHandler,
                            LogoutFilter::class.java)
                    .addFilterBefore(
                            JWTAuthenticationFilter("/user/login", auth = authenticationManager(), jwtUtil = jwtUtil),
                            UsernamePasswordAuthenticationFilter::class.java)
                    .addFilterBefore(
                            JWTAuthorizationFilter(authenticationManager(), jwtUtil = jwtUtil, userDetailService = userDetailsService),
                            UsernamePasswordAuthenticationFilter::class.java
                    )
                }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers(
                "/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui",
                "/webjars/**"
                )
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }


    @Bean
    fun bCryptPasswordEncoder() : BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}