package ru.kuraecode.backend.web.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import ru.kuraecode.backend.domain.BookKeepingInfo
import java.math.BigDecimal
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
class UpdateBookKeepingInfoDto {

    var id: Long = -1
    var structuralSubdivision: String = ""
    var fixSalary : BigDecimal = BigDecimal(0)
    var dayWorks : Int = -1
    var dayWorksExpect: Int = -1
    var dayStart: LocalDate = LocalDate.MIN
    var dayEnd: LocalDate = LocalDate.MIN
    var personId: Long? = null

    /*fun fromPerson(bookKeepingInfo: BookKeepingInfo): UpdateBookKeepingInfoDto {
        id = bookKeepingInfo.id!!
        structuralSubdivision = bookKeepingInfo.structuralSubdivision
        fixSalary = bookKeepingInfo.fixSalary
        dayWorks = bookKeepingInfo.dayWorks
        dayWorksExpect = bookKeepingInfo.dayWorksExpect
        dayStart = bookKeepingInfo.dayStart
        dayEnd = bookKeepingInfo.dayEnd
        personId = bookKeepingInfo.personId
        return this
    }*/

    fun toBookKeepingInfo(): BookKeepingInfo {
        val bookKeeping =  BookKeepingInfo()
        bookKeeping.id = this.id
        bookKeeping.structuralSubdivision = this.structuralSubdivision
        bookKeeping.fixSalary = this.fixSalary
        bookKeeping.dayWorks = this.dayWorks
        bookKeeping.dayWorksExpect = this.dayWorksExpect
        bookKeeping.dayStart = this.dayStart
        bookKeeping.dayEnd = this.dayEnd
        bookKeeping.personId = this.personId
        return bookKeeping
    }
}
