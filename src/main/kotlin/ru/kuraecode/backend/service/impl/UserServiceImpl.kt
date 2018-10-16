package ru.kuraecode.backend.service.impl

import org.springframework.stereotype.Service
import ru.kuraecode.backend.domain.User
import ru.kuraecode.backend.service.UserService

@Service
class UserServiceImpl : UserService {


    override fun getUserById(id: Long): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllUser(): List<User> {
        TODO("not implemented")
    }

    override fun createUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}