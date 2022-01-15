package com.iban.subscriptionsapi.infrastructure.db

import com.iban.subscriptionsapi.application.protocols.SubscriptionCreationRepository
import com.iban.subscriptionsapi.domain.models.Subscription
import org.springframework.stereotype.Component

@Component
class SubscriptionRepository(private val subscriptionJpaRepository: SubscriptionJpaRepository) :
    SubscriptionCreationRepository {
    override fun save(subscription: Subscription): Subscription {
        return subscriptionJpaRepository.save(subscription)
    }
}