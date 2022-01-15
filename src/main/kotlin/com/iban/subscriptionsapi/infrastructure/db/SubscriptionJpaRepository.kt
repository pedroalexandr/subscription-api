package com.iban.subscriptionsapi.infrastructure.db

import com.iban.subscriptionsapi.domain.models.Subscription
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionJpaRepository : JpaRepository<Subscription, Long> {
}