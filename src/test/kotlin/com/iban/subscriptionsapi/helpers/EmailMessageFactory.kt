package com.iban.subscriptionsapi.helpers

import com.github.javafaker.Faker
import com.iban.subscriptionsapi.domain.data.EmailMessage

class EmailMessageFactory {
    fun make(email: String): EmailMessage {
        val faker = Faker()
        return EmailMessage(
            content = faker.lorem().sentence(),
            from = faker.internet().emailAddress(),
            to = email,
            subject = faker.lorem().sentence(3)
        )
    }
}