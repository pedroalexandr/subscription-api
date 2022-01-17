package com.iban.subscriptionsapi.helpers

import com.github.javafaker.Faker
import com.iban.subscriptionsapi.domain.models.Subscription
import java.time.LocalDate
import java.time.Month

class SubscriptionFactory {
    private val _dateOfBirth = LocalDate.of(1990, Month.APRIL, 21)

    fun make(): Subscription {
        val faker = Faker()
        return Subscription(
            email = faker.internet().emailAddress(),
            firstName = faker.name().firstName(),
            gender = "O",
            dateOfBirth = _dateOfBirth,
            optIn = true,
            newsletterId = 1
        )
    }

    fun makePartial(): Any {
        val faker = Faker()
        return object {
            val firstName = faker.name().firstName()
            val gender = "O"
            val dateOfBirth = _dateOfBirth
            val optIn = true
            val newsletterId = 1
        }
    }
}