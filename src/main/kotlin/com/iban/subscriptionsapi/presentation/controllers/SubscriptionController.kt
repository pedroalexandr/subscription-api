package com.iban.subscriptionsapi.presentation.controllers

import com.iban.subscriptionsapi.domain.model.Subscription
import com.iban.subscriptionsapi.infrastructure.db.SubscriptionRepository
import com.iban.subscriptionsapi.infrastructure.services.EmailServiceImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/subscriptions")
class SubscriptionController(
    private val subscriptionRepository: SubscriptionRepository,
    private val emailService: EmailServiceImpl
) {
    @PostMapping
    fun create(@RequestBody subscription: Subscription): Subscription {
        val createdSubscription = subscriptionRepository.save(subscription)

        emailService.sendSimpleMessage(createdSubscription.email, "Subscription confirmation", "Confirmed")

        return createdSubscription
    }
}