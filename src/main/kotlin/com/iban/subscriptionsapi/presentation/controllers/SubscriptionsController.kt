package com.iban.subscriptionsapi.presentation.controllers

import com.iban.subscriptionsapi.domain.models.Subscription
import com.iban.subscriptionsapi.domain.usecases.SubscriptionCreation
import com.iban.subscriptionsapi.domain.usecases.TransactionalEmailSending
import com.iban.subscriptionsapi.helpers.EmailMessageFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/subscriptions")
class SubscriptionsController(
    private val subscriptionCreation: SubscriptionCreation,
    private val transactionalEmailSending: TransactionalEmailSending
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody subscription: Subscription): Subscription {
        val createdSubscription = subscriptionCreation.create(subscription)
        val emailMessage = EmailMessageFactory().create(createdSubscription.email)
        transactionalEmailSending.send(emailMessage)
        return createdSubscription
    }
}