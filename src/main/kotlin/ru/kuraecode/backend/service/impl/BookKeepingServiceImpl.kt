package ru.kuraecode.backend.service.impl

import org.springframework.stereotype.Service
import ru.kuraecode.backend.domain.BookKeepingInfo
import ru.kuraecode.backend.domain.PersonReportEntity
import ru.kuraecode.backend.repository.BookKeepingInfoRepository
import ru.kuraecode.backend.service.BookKeepingService
import ru.kuraecode.backend.service.ReportGenerator
import java.time.LocalDate

@Service
class BookKeepingServiceImpl(val bookKeepingInfoRepository: BookKeepingInfoRepository,
                             val reportGenerator: ReportGenerator) : BookKeepingService {



    override fun createBookkeepingForUser(personId: Long, bookKeepingInfo: BookKeepingInfo, periodDate: LocalDate): BookKeepingInfo {
        bookKeepingInfo.personId = personId
        bookKeepingInfo.dayStart = LocalDate.of(periodDate.year, periodDate.month, 1)
        bookKeepingInfo.dayEnd = LocalDate.of(periodDate.year, periodDate.month, periodDate.lengthOfMonth())
        return bookKeepingInfoRepository.save(bookKeepingInfo)
    }

    override fun updateBookkeeping(bookKeepingInfo: BookKeepingInfo) {
        bookKeepingInfo.dayEnd = LocalDate.of(bookKeepingInfo.dayStart.year, bookKeepingInfo.dayStart.month, bookKeepingInfo.dayStart.lengthOfMonth())
        bookKeepingInfoRepository.save(bookKeepingInfo)

    }

    override fun deleteBookkeeping(id: Long) {
        bookKeepingInfoRepository.deleteById(id)
    }
}