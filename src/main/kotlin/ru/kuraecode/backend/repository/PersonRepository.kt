package ru.kuraecode.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.kuraecode.backend.domain.Person
import java.time.LocalDate


@Repository
interface PersonRepository : JpaRepository<Person, Long> {

    @Query("Select distinct p from Person p left join fetch p.bookKeepingInfo b where b.dayStart <= :date and b.dayEnd >= :date")
    fun findPersonWithDateBookkeeping(date: LocalDate) : List<Person>
}