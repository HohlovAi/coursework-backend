package ru.kuraecode.backend.web

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kuraecode.backend.configuration.jwt.JwtTokenProvider
import ru.kuraecode.backend.domain.User
import ru.kuraecode.backend.repository.UserRepository
import ru.kuraecode.backend.web.dto.JwtTokenDto
import ru.kuraecode.backend.web.dto.LoginRequest
import ru.kuraecode.backend.web.dto.CreateUserDto


//TODO: remove business logic from controller
@RestController
@RequestMapping("/account")
class AccountController(val authenticationManager: AuthenticationManager,
                        val jwtTokenProvider: JwtTokenProvider,
                        val userRepository: UserRepository,
                        val passwordEncoder: PasswordEncoder) {

    @PostMapping("/signin")
    fun signIn(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {

        if (loginRequest.isEmpty()) return ResponseEntity.badRequest().build()
        try {
            with(loginRequest) {
                authenticationManager.authenticate(UsernamePasswordAuthenticationToken(login, password))
                return ResponseEntity.ok(JwtTokenDto(jwtTokenProvider.createToken(login, userRepository.findByLogin(login).getRoles())))
            }
        } catch (e: AuthenticationException) {
            return ResponseEntity.status(401).body("Invalid credentials")
        }
    }

    @PostMapping("/signup")
    fun signup(@RequestBody createCreateUserDto: CreateUserDto): ResponseEntity<Void> {
        val user = User(login = createCreateUserDto.login, password = passwordEncoder.encode(createCreateUserDto.password), isEnable = true)
        userRepository.save(user)
        return ResponseEntity.ok().build<Void>()
    }
}
