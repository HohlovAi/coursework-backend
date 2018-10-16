package ru.kuraecode.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kuraecode.backend.domain.User

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByLogin(login: String) : User
}