package ru.kuraecode.backend.configuration.jwt

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtTokenFilter(private val jwtTokenProvider: JwtTokenProvider) : GenericFilterBean() {


    override fun doFilter(req: ServletRequest, res: ServletResponse, filterChain: FilterChain) {

        val token = jwtTokenProvider.resolveToken(req as HttpServletRequest)
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                val auth = jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (ex: CustomException) {
            val response = res as HttpServletResponse
            response.sendError(ex.httpStatus.value(), ex.message)
            return
        }

        filterChain.doFilter(req, res)
    }

}