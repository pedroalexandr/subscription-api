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

    fun makeWithEmptyEmail(): Subscription {
        val faker = Faker()
        return Subscription(
            email = "",
            firstName = faker.name().firstName(),
            gender = "O",
            dateOfBirth = _dateOfBirth,
            optIn = true,
            newsletterId = 1
        )
    }

    fun makeWithInvalidEmail(): Subscription {
        val faker = Faker()
        return Subscription(
            email = "invalid_email",
            firstName = faker.name().firstName(),
            gender = "O",
            dateOfBirth = _dateOfBirth,
            optIn = true,
            newsletterId = 1
        )
    }

    fun makeWithInvalidGender(): Subscription {
        val faker = Faker()
        return Subscription(
            email = faker.internet().emailAddress(),
            firstName = faker.name().firstName(),
            gender = "invalid",
            dateOfBirth = _dateOfBirth,
            optIn = true,
            newsletterId = 1
        )
    }

    fun makeWithInvalidDateOfBirth(): Subscription {
        val faker = Faker()
        return Subscription(
            email = faker.internet().emailAddress(),
            firstName = faker.name().firstName(),
            gender = "O",
            dateOfBirth = LocalDate.now(),
            optIn = true,
            newsletterId = 1
        )
    }

    fun makeWithInvalidNewsletterId(): Subscription {
        val faker = Faker()
        return Subscription(
            email = faker.internet().emailAddress(),
            firstName = faker.name().firstName(),
            gender = "O",
            dateOfBirth = _dateOfBirth,
            optIn = true,
            newsletterId = -10
        )
    }
}