package com.iban.subscriptionsapi.infrastructure.db

import com.iban.subscriptionsapi.domain.model.Subscription
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Long> {}