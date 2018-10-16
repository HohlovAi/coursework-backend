package ru.kuraecode.backend.domain

import javax.persistence.*

@Entity
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var firstName: String = "",
        var lastName: String = "",
        var middleName: String = "",
        @OneToMany(mappedBy = "personId", orphanRemoval = true)
        var bookKeepingInfo: MutableList<BookKeepingInfo> = ArrayList()
)