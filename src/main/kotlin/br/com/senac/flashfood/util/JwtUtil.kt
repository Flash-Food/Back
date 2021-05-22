package br.com.senac.flashfood.util

import br.com.senac.flashfood.constant.JWTConstants
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    private val expiration: Long = 60000

    fun generateToken(username: String, authority: List<GrantedAuthority>) = Jwts.builder()
                .setSubject(username)
                .claim(JWTConstants.AUTHORITIES.getValue(), getAuthorities(authority))
                .setExpiration(Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
                .compact()


    fun isTokenValid(token: String): Boolean {
        val claims = getClaimsToken(token)
        if (claims != null) {
            val username = claims.subject
            val expirationDate = claims.expiration
            val authorities = claims.getValue(JWTConstants.AUTHORITIES.getValue())
            val now = Date(System.currentTimeMillis())
            if (username != null && authorities != null && expirationDate != null && now.before(expirationDate)) {
                return true
            }
        }
        return false
    }


    private fun getClaimsToken(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e: Exception) {
            null
        }
    }

    fun getUserName(token: String): String? {
        val claims = getClaimsToken(token)
        return claims?.subject
    }

    fun getAuthorities(authorities: List<GrantedAuthority>) = authorities
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList())
}