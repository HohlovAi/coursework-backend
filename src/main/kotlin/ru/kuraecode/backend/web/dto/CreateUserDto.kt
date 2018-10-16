package ru.kuraecode.backend.web.dto

data class CreateUserDto(
        var login: String = "",
        var password: String = ""
)
