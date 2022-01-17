package com.iban.subscriptionsapi.presentation.controllers

import com.iban.subscriptionsapi.domain.data.EmailMessage
import com.iban.subscriptionsapi.domain.models.Subscription
import com.iban.subscriptionsapi.domain.usecases.SubscriptionCreation
import com.iban.subscriptionsapi.domain.usecases.TransactionalEmailSending
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/subscriptions")
class SubscriptionsController(
    private val subscriptionCreation: SubscriptionCreation,
    private val transactionalEmailSending: TransactionalEmailSending,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody subscription: Subscription): Subscription {
        val createdSubscription = subscriptionCreation.create(subscription)
        val emailMessage = EmailMessage(
            content = "Yay",
            to = createdSubscription.email,
            from = "marketing@iban.com",
            subject = "Subscription confirmed"
        )
        transactionalEmailSending.send(emailMessage)
        return createdSubscription
    }
}