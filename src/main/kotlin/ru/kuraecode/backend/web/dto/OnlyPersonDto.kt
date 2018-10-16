package ru.kuraecode.backend.web.dto

import ru.kuraecode.backend.domain.Person

class OnlyPersonDto {
    var name: String = ""
    var lastName: String = ""
    var middleName: String = ""

    fun toPerson() : Person {
        return Person(
                firstName = this.name,
                lastName = this.lastName,
                middleName = this.middleName)
    }

}