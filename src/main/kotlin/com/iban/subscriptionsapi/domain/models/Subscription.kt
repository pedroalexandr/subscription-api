package com.iban.subscriptionsapi.domain.models

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

enum class Gender(val char: Char) {
    MALE('M'),
    FEMALE('F'),
    OTHER('O'),
}

@Entity(name = "subscriptions")
data class Subscription(
    @Id @GeneratedValue
    val id: Long? = null,
    val email: String,
    val firstName: String = "",
    val gender: Char = Gender.OTHER.char,
    val dateOfBirth: LocalDate,
    val optIn: Boolean,
    val newsletterId: Long,
)