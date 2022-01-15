package com.iban.subscriptionsapi.domain.usecases

import com.iban.subscriptionsapi.domain.models.EmailMessage

interface TransactionalEmailSending {
    fun send(emailMessage: EmailMessage): Void?
}