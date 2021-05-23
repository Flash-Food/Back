package br.com.senac.flashfood.util

import br.com.senac.flashfood.constant.JWTConstants
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
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

    private val expiration: Long = 3600000

    private val TIME_TORELANCE = 5

    private val BASE_DIV_TIME = 60000

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
            if (username != null && authorities != null && expirationDate != null) {
                return true
            }
        }
        return false
    }

    fun isTokenExpirated(token: String): Boolean {
        val claims = getClaimsToken(token)
        if(claims != null) {
            val now = Date(System.currentTimeMillis())
            if(now.after(claims.expiration))
                return true
        }
        return false
    }

    fun validTimeTolerance(token: String, tolerance: Date): Boolean {
        val finalAccess = tolerance.time / BASE_DIV_TIME
        val expirationTime = (getClaimsToken(token)?.expiration?.time?:5L) / BASE_DIV_TIME
        val result = expirationTime - finalAccess
        if (result.toInt() <= TIME_TORELANCE) return true
        return false
    }


    private fun getClaimsToken(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e: ExpiredJwtException) {
            // TODO: GET CLAIMS WITH EXPIRATED DATE FOR VALIDATION
            return e.claims
        } catch (e: Exception) {
            // TODO: GET OTHERS ERRORS
            e.printStackTrace()
            return null
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