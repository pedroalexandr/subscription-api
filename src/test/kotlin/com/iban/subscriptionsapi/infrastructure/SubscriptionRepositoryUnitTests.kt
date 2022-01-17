package com.iban.subscriptionsapi.infrastructure

import com.iban.subscriptionsapi.helpers.SubscriptionFactory
import com.iban.subscriptionsapi.infrastructure.db.SubscriptionJpaRepository
import com.iban.subscriptionsapi.infrastructure.db.SubscriptionRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class SubscriptionRepositoryUnitTests {
    @Test
    fun `should call SubscriptionJpaRepository's save method successfully`() {
        val mockSubscriptionJpaRepository = mock(SubscriptionJpaRepository::class.java)
        val sut = SubscriptionRepository(mockSubscriptionJpaRepository)
        val givenSubscription = SubscriptionFactory().make()
        `when`(mockSubscriptionJpaRepository.save(givenSubscription)).thenReturn(givenSubscription)

        sut.save(givenSubscription)

        verify(mockSubscriptionJpaRepository, times(1)).save(givenSubscription)
    }
}