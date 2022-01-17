package com.iban.subscriptionsapi.domain.usecases

import com.iban.subscriptionsapi.domain.data.EmailMessage

interface TransactionalEmailSending {
    fun send(emailMessage: EmailMessage): Void?
}