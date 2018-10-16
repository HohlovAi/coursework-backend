package ru.kuraecode.backend.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.kuraecode.backend.service.PersonService
import ru.kuraecode.backend.web.dto.FullPersonDto
import ru.kuraecode.backend.web.dto.OnlyPersonDto

@RestController
@RequestMapping("/api/persons")
class PersonController(val personService: PersonService) {

    @GetMapping
    fun getPersons() = ResponseEntity.ok(personService.getAllPersons().map { FullPersonDto().fromPerson(it) })

    @GetMapping("/current")
    fun getPersonsCurrent() = ResponseEntity.ok(personService.getPersonsWithCurrentBookkeepeingInfo().map { FullPersonDto().fromPerson(it) })

    @PutMapping("/{id}")
    fun updatePerson(@PathVariable id:Long, @RequestBody person: OnlyPersonDto): ResponseEntity<Void> {
        val toPerson = person.toPerson()
        toPerson.id = id
        personService.updatePerson(toPerson)
        return ResponseEntity.ok().build<Void>()
    }
    @PostMapping
    fun createPerson(@RequestBody person: OnlyPersonDto) = ResponseEntity.ok(FullPersonDto().fromPerson(personService.createPerson(person.toPerson())))

    @GetMapping("/report")
    fun generateReport() = ResponseEntity.ok(personService.generateReport())

}