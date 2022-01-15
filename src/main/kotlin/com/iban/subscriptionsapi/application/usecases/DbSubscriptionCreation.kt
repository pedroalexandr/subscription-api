package com.iban.subscriptionsapi.application.usecases

import com.iban.subscriptionsapi.application.protocols.SubscriptionCreationRepository
import com.iban.subscriptionsapi.domain.models.Subscription
import com.iban.subscriptionsapi.domain.usecases.SubscriptionCreation
import org.springframework.stereotype.Component

@Component
class DbSubscriptionCreation(
    private val subscriptionCreationRepository: SubscriptionCreationRepository
) : SubscriptionCreation {
    override fun create(subscription: Subscription): Subscription {
        return subscriptionCreationRepository.save(subscription)
    }
}