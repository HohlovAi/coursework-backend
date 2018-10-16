package ru.kuraecode.backend.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import ru.kuraecode.backend.configuration.jwt.JwtTokenFilterConfigurer
import ru.kuraecode.backend.configuration.jwt.JwtTokenProvider

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration(val jwtTokenProvider: JwtTokenProvider) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {

        http!!.csrf().disable().headers().frameOptions().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeRequests()
                .antMatchers("/account/signin").permitAll()
                .antMatchers("/account/signup").permitAll()
                .antMatchers("/h2/**/**").permitAll()
                .anyRequest().authenticated()
        http.apply(JwtTokenFilterConfigurer(jwtTokenProvider))
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authManager(): AuthenticationManager = super.authenticationManager()
}