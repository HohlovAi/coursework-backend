package ru.kuraecode.backend.service

import ru.kuraecode.backend.domain.Person
import ru.kuraecode.backend.domain.PersonReportEntity

interface ReportGenerator {

    fun generateStandartReport(persons: List<Person>): List<PersonReportEntity>
}