package com.iban.subscriptionsapi.application.usecases

import com.iban.subscriptionsapi.application.protocols.QueueMessagePublisher
import com.iban.subscriptionsapi.domain.data.EmailMessage
import com.iban.subscriptionsapi.domain.usecases.TransactionalEmailSending
import org.springframework.stereotype.Component

const val QUEUE_KEY_FOR_TRANSACTIONAL_EMAILS = "transactional-emails"

@Component
class TransactionalSubscriptionEmailSending(private val queueMessagePublisher: QueueMessagePublisher) :
    TransactionalEmailSending {
    override fun send(emailMessage: EmailMessage): Void? {
        queueMessagePublisher.publish(QUEUE_KEY_FOR_TRANSACTIONAL_EMAILS, emailMessage)
        return null
    }
}