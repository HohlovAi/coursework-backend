package ru.kuraecode.backend.domain

import java.time.LocalDate


/**
 * начисленная зп = фиксированный оклад * отработанных дней / на норма дней
 * ндфл = 13% от начисленной зп
 * сумма страховых взносов 30.2% от начиленной зп
 * зп на руки = начисленная зп - ндфл - сраховые взносы
 */


data class PersonReportEntity(
        val personId: Long = -1,
        val firstName: String = "",
        val lastName: String = "",
        val info: List<InfoReportEntity> = emptyList()
)

data class InfoReportEntity(
        val date: LocalDate = LocalDate.MIN,
        val grossSalary: Double = -1.0,
        val ndfl: Double = -1.0,
        val insurancePremiums: Double = -1.0,
        val actuallyGrossSalary: Double = -1.0
)