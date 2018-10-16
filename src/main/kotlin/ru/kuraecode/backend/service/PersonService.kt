package ru.kuraecode.backend.service

import ru.kuraecode.backend.domain.Person
import ru.kuraecode.backend.domain.PersonReportEntity

interface PersonService {

    fun generateReport(): List<PersonReportEntity>

    fun getPersonById(id: Long)

    fun getAllPersons(): List<Person>

    fun getPersonsWithCurrentBookkeepeingInfo() : List<Person>

    fun updatePerson(toPerson: Person)

    fun createPerson(person: Person): Person
}