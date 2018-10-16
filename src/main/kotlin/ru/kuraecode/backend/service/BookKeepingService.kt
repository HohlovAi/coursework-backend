package ru.kuraecode.backend.service

import ru.kuraecode.backend.domain.BookKeepingInfo
import ru.kuraecode.backend.domain.PersonReportEntity
import java.time.LocalDate

interface BookKeepingService {

    fun createBookkeepingForUser(personId: Long, bookKeepingInfo: BookKeepingInfo, periodDate: LocalDate): BookKeepingInfo

    fun updateBookkeeping(bookKeepingInfo: BookKeepingInfo)

    fun deleteBookkeeping(id: Long)
}