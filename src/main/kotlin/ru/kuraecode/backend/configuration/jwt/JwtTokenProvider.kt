package ru.kuraecode.backend.configuration.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import ru.kuraecode.backend.domain.Role
import java.util.*
import javax.annotation.PostConstruct
import org.springframework.http.HttpStatus
import io.jsonwebtoken.JwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(@Qualifier("ownUserDetailsService") val userDetailsService: UserDetailsService) {

    @Value("\${security.jwt.token.secret-key:secret-key}")
    lateinit var secretKey: String

    @Value("\${security.jwt.token.expire-length:3600000}")
    var validityInMilliseconds = 3600000L

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    fun createToken(username: String, roles: List<Role>): String {

        val claims = Jwts.claims().setSubject(username)

        claims["auth"] = roles
                .asSequence()
                .map { s -> SimpleGrantedAuthority(s.authority) }
                .filterNotNull()
                .toList()

        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            return true
        } catch (e: ExpiredJwtException) {
            throw CustomException("Expired JWT token", HttpStatus.UPGRADE_REQUIRED)
        } catch (e: JwtException) {
            throw CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: IllegalArgumentException) {
            throw CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }
}