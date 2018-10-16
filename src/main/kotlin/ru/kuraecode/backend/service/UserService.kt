package ru.kuraecode.backend.service

import ru.kuraecode.backend.domain.User

interface UserService {

    fun getUserById(id: Long) : User

    fun getAllUser() : List<User>

    fun createUser(user: User)

    fun updateUser(user: User)
}