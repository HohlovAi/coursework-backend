package ru.kuraecode.backend.service.impl

import org.springframework.stereotype.Service
import ru.kuraecode.backend.domain.BookKeepingInfo
import ru.kuraecode.backend.domain.InfoReportEntity
import ru.kuraecode.backend.domain.Person
import ru.kuraecode.backend.domain.PersonReportEntity
import ru.kuraecode.backend.service.ReportGenerator
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * начисленная зп = фиксированный оклад * отработанных дней / на норма дней
 * ндфл = 13% от начисленной зп
 * сумма страховых взносов 30.2% от начиленной зп
 * зп на руки = начисленная зп - ндфл - сраховые взносы
 */

@Service
class ReportGeneratorImpl : ReportGenerator {

    private val ndflPercent = 13.0
    private val insurancePremiums = 30.2

    override fun generateStandartReport(persons: List<Person>): List<PersonReportEntity> {
        return persons
                .asSequence()
                .map {
                    PersonReportEntity(
                            personId = it.id!!,
                            firstName = it.firstName,
                            lastName = it.lastName,
                            info = it.bookKeepingInfo
                                    .map { bookkeepingInfo ->
                                        calculateGrossSalary(bookkeepingInfo).let { gross ->
                                            calculateNdfl(gross).let { ndfl ->
                                                calculateInsurancePremiums(gross).let { premiums ->
                                                    InfoReportEntity(
                                                            date = bookkeepingInfo.dayStart,
                                                            grossSalary = gross.toDouble(),
                                                            ndfl = ndfl.toDouble(),
                                                            insurancePremiums = premiums.toDouble(),
                                                            actuallyGrossSalary = calculateActualSalary(grossSalary = gross, ndfl = ndfl, insureancePermiums = premiums).toDouble())

                                                }
                                            }
                                        }
                                    }
                            )
                }
                .toList()
    }

    private fun calculateGrossSalary(bookKeepingInfo: BookKeepingInfo): BigDecimal {
        return with(bookKeepingInfo) {
            fixSalary.multiply(dayWorks.toBigDecimal()).divide(dayWorksExpect.toBigDecimal(), RoundingMode.HALF_UP)
        }
    }

    private fun calculateNdfl(grossSalary: BigDecimal) = grossSalary.atPercent(ndflPercent)

    private fun calculateInsurancePremiums(grossSalary: BigDecimal) = grossSalary.atPercent(insurancePremiums)

    private fun calculateActualSalary(grossSalary: BigDecimal, ndfl: BigDecimal, insureancePermiums: BigDecimal) = grossSalary.minus(ndfl).minus(insureancePermiums)
}

private fun BigDecimal.atPercent(percent: Double): BigDecimal {
    return this.divide(100.toBigDecimal(), RoundingMode.HALF_UP).multiply(percent.toBigDecimal())
}

