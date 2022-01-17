package com.iban.subscriptionsapi.usecases

import com.github.javafaker.Faker
import com.iban.subscriptionsapi.application.protocols.QueueMessagePublisher
import com.iban.subscriptionsapi.application.usecases.TransactionalSubscriptionEmailSending
import com.iban.subscriptionsapi.helpers.EmailMessageFactory
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class TransactionalSubscriptionEmailSendingUnitTests {
    @Test
    fun `should call QueueMessagePublisher's publish method`() {
        val mockQueueMessagePublisher = Mockito.mock(QueueMessagePublisher::class.java)
        val sut = TransactionalSubscriptionEmailSending(mockQueueMessagePublisher)
        val fakeEmail = Faker().internet().emailAddress()
        val emailMessage = EmailMessageFactory().make(fakeEmail)

        sut.send(emailMessage)

        verify(mockQueueMessagePublisher, times(1)).publish("transactional-emails", emailMessage)
    }
}