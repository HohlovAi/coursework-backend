package ru.kuraecode.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.kuraecode.backend.domain.BookKeepingInfo
import java.time.LocalDate

@Repository
interface BookKeepingInfoRepository : JpaRepository<BookKeepingInfo, Long> {


    @Query("Select distinct b from BookKeepingInfo b where b.dayStart <= :date and b.dayEnd >= :date and b.personId = :personId")
    fun findWithDateAndPersonId(date: LocalDate, personId: Long) : BookKeepingInfo

}