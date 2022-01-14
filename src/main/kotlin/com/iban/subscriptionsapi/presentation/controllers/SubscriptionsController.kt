package com.iban.subscriptionsapi.presentation.controllers

import com.iban.subscriptionsapi.domain.model.Subscription
import com.iban.subscriptionsapi.infrastructure.db.SubscriptionRepository
import com.iban.subscriptionsapi.infrastructure.services.EmailServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/subscriptions")
class SubscriptionsController(
    private val subscriptionRepository: SubscriptionRepository,
    private val emailService: EmailServiceImpl
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody subscription: Subscription): Subscription {
        val createdSubscription = subscriptionRepository.save(subscription)

        emailService.sendSimpleMessage(createdSubscription.email, "Subscription confirmation", "Confirmed")

        return createdSubscription
    }
}