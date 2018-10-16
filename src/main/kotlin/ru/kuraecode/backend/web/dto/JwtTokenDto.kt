package ru.kuraecode.backend.web.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
data class JwtTokenDto(
        val token: String = ""
)