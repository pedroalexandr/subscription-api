package com.iban.subscriptionsapi.helpers

import com.github.javafaker.Faker
import com.iban.subscriptionsapi.domain.models.Subscription
import java.time.LocalDate

class SubscriptionFactory {
    fun create(): Subscription {
        val faker = Faker()
        return Subscription(
            email = faker.internet().emailAddress(),
            firstName = faker.name().firstName(),
            dateOfBirth = LocalDate.now(),
            optIn = true,
            newsletterId = 1
        )
    }

    fun createPartial(): Any {
        val faker = Faker()
        return object {
            val firstName = faker.name().firstName()
            val dateOfBirth = LocalDate.now()
            val optIn = true
            val newsletterId = 1
        }
    }
}