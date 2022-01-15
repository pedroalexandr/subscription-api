package com.iban.subscriptionsapi.domain.usecases

import com.iban.subscriptionsapi.domain.models.Subscription

interface SubscriptionCreation {
    fun create(subscription: Subscription): Subscription
}