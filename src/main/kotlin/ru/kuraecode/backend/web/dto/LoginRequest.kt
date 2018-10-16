package ru.kuraecode.backend.web.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginRequest(
        var login: String = "",
        var password: String = "") {

    fun isEmpty(): Boolean {
        return login.isEmpty() || password.isEmpty()
    }
}