package ru.kuraecode.backend.domain

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * структурное подразделение, фикс оклалд, отработанных дней, норма дней по производственному коллендарю
 */

@Entity
data class BookKeepingInfo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var structuralSubdivision: String = "",
        var fixSalary : BigDecimal = BigDecimal(0),
        var dayWorks : Int = -1,
        var dayWorksExpect: Int = -1,
        var dayStart: LocalDate = LocalDate.MIN,
        var dayEnd: LocalDate = LocalDate.MIN,
        var personId: Long? = null
)