package com.iban.subscriptionsapi.application.usecases

import com.iban.subscriptionsapi.application.protocols.QueueMessagePublisher
import com.iban.subscriptionsapi.domain.models.EmailMessage
import com.iban.subscriptionsapi.domain.usecases.TransactionalEmailSending
import org.springframework.stereotype.Component

@Component
class TransactionalSubscriptionEmailSending(private val queueMessagePublisher: QueueMessagePublisher) :
    TransactionalEmailSending {
    override fun send(emailMessage: EmailMessage): Void? {
        queueMessagePublisher.publish("transactional-emails", emailMessage)
        return null
    }
}