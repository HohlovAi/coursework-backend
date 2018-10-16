package ru.kuraecode.backend.service.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kuraecode.backend.domain.Person
import ru.kuraecode.backend.domain.PersonReportEntity
import ru.kuraecode.backend.repository.BookKeepingInfoRepository
import ru.kuraecode.backend.repository.PersonRepository
import ru.kuraecode.backend.service.PersonService
import ru.kuraecode.backend.service.ReportGenerator
import java.time.LocalDate

@Service
class PersonServiceImpl(val personRepository: PersonRepository,
                        val bookKeepingInfoRepository: BookKeepingInfoRepository,
                        val reportGenerator: ReportGenerator) : PersonService {

    override fun generateReport(): List<PersonReportEntity> {
        return reportGenerator.generateStandartReport(this.getAllPersons())
    }

    override fun createPerson(person: Person): Person {
        return personRepository.save(person)
    }



    @Transactional
    override fun updatePerson(toPerson: Person) {
        personRepository.findById(toPerson.id!!).ifPresent {
            it.firstName = toPerson.firstName
            it.lastName = toPerson.lastName
            it.middleName = toPerson.middleName
            personRepository.save(it)
        }
    }

    override fun getPersonsWithCurrentBookkeepeingInfo(): List<Person> {
        return personRepository
                .findPersonWithDateBookkeeping(LocalDate.now())
    }

    override fun getPersonById(id: Long) {

    }

    override fun getAllPersons(): List<Person> {
        return personRepository
                .findAll()
    }


}