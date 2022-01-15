package com.iban.subscriptionsapi.application.protocols

import com.iban.subscriptionsapi.domain.models.Subscription

interface SubscriptionCreationRepository {
    fun save(subscription: Subscription): Subscription
}