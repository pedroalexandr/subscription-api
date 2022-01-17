package com.iban.subscriptionsapi.usecases

import com.iban.subscriptionsapi.application.protocols.SubscriptionCreationRepository
import com.iban.subscriptionsapi.application.usecases.DbSubscriptionCreation
import com.iban.subscriptionsapi.helpers.SubscriptionFactory
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class DbSubscriptionCreationUnitTests {
    @Test
    fun `should call SubscriptionCreationRepository's save method`() {
        val mockSubscriptionCreationRepository = Mockito.mock(SubscriptionCreationRepository::class.java)
        val sut = DbSubscriptionCreation(mockSubscriptionCreationRepository)
        val givenSubscription = SubscriptionFactory().make()

        sut.create(givenSubscription)

        verify(mockSubscriptionCreationRepository, times(1)).save(givenSubscription)
    }
}