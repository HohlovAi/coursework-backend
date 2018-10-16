package ru.kuraecode.backend.web.dto

import ru.kuraecode.backend.domain.BookKeepingInfo
import java.math.BigDecimal
import java.time.LocalDate

class CreateBookKeepingInfoDto {

    var structuralSubdivision: String = ""
    var fixSalary : BigDecimal = BigDecimal(0)
    var dayWorks : Int = -1
    var dayWorksExpect: Int = -1
    var dayStart: LocalDate = LocalDate.MIN
    var dayEnd: LocalDate = LocalDate.MIN
    var inputDate: LocalDate = LocalDate.MIN
    var personId: Long? = null

    fun fromPerson(bookKeepingInfo: BookKeepingInfo): CreateBookKeepingInfoDto {
        structuralSubdivision = bookKeepingInfo.structuralSubdivision
        fixSalary = bookKeepingInfo.fixSalary
        dayWorks = bookKeepingInfo.dayWorks
        dayWorksExpect = bookKeepingInfo.dayWorksExpect
        dayStart = bookKeepingInfo.dayStart
        dayEnd = bookKeepingInfo.dayEnd
        personId = bookKeepingInfo.personId
        return this
    }

    fun toBookKeepingInfo(): BookKeepingInfo {
        val bookKeeping =  BookKeepingInfo()
        bookKeeping.structuralSubdivision = this.structuralSubdivision
        bookKeeping.fixSalary = this.fixSalary
        bookKeeping.dayWorks = this.dayWorks
        bookKeeping.dayWorksExpect = this.dayWorksExpect

        return bookKeeping
    }
}