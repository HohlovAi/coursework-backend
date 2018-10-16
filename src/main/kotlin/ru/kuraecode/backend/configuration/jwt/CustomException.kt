package ru.kuraecode.backend.configuration.jwt

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

class CustomException(val str: String, val httpStatus: HttpStatus) : RuntimeException(str)
