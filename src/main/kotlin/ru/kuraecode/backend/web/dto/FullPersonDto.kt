package ru.kuraecode.backend.web.dto

import ru.kuraecode.backend.domain.Person

class FullPersonDto {

    var id: Long? = null
    var name: String = ""
    var lastName: String = ""
    var middleName: String = ""
    var bookKeepings = emptyList<FullBookKeepingInfoDto>()

    fun fromPerson(person: Person): FullPersonDto {
        id = person.id
        name = person.firstName
        lastName = person.lastName
        middleName = person.middleName
        bookKeepings = person.bookKeepingInfo.map { FullBookKeepingInfoDto().fromPerson(it) }
        return this
    }
}