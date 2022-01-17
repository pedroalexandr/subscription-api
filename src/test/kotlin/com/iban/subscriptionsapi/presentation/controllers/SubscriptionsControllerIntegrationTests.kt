package com.iban.subscriptionsapi.presentation.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.iban.subscriptionsapi.domain.data.EmailMessage
import com.iban.subscriptionsapi.domain.usecases.SubscriptionCreation
import com.iban.subscriptionsapi.domain.usecases.TransactionalEmailSending
import com.iban.subscriptionsapi.helpers.SubscriptionFactory
import com.iban.subscriptionsapi.infrastructure.db.SubscriptionJpaRepository
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.boot.test.context.SpringBootTest

const val CONTROLLER_ROUTE = "/api/v1/subscriptions"

@SpringBootTest
@AutoConfigureMockMvc
class SubscriptionsControllerIntegrationTests() {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var subscriptionCreation: SubscriptionCreation

    @Mock
    lateinit var mockSubscriptionJpaRepository: SubscriptionJpaRepository

    @MockBean
    lateinit var mockTransactionalEmailSending: TransactionalEmailSending

    private val mapper = ObjectMapper()
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    @Test
    fun `should return status 201 CREATED with created subscription`() {
        val givenSubscription = SubscriptionFactory().make()
        val stringifiedSubscription = mapper.writeValueAsString(givenSubscription)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CONTROLLER_ROUTE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringifiedSubscription)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.email").value(givenSubscription.email))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.firstName").value(givenSubscription.firstName))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.gender").value(givenSubscription.gender))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.dateOfBirth").value(givenSubscription.dateOfBirth.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.optIn").value(givenSubscription.optIn))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.newsletterId").value(givenSubscription.newsletterId))

        verify(mockTransactionalEmailSending, times(1)).send(EmailMessage(
            content = "Yay",
            to = givenSubscription.email,
            from = "marketing@iban.com",
            subject = "Subscription confirmed"
        ))
    }

    @Test
    fun `should return bad request 400 given email is empty`() {
        val subscriptionMissingRequiredField = SubscriptionFactory().makeWithEmptyEmail()
        val stringifiedSubscription = mapper.writeValueAsString(subscriptionMissingRequiredField)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CONTROLLER_ROUTE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringifiedSubscription)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

        verify(mockSubscriptionJpaRepository, times(0)).save(subscriptionMissingRequiredField)
        verify(mockTransactionalEmailSending, times(0)).send(EmailMessage(
            content = "Yay",
            to = subscriptionMissingRequiredField.email,
            from = "marketing@iban.com",
            subject = "Subscription confirmed"
        ))
    }

    @Test
    fun `should return bad request 400 when given email is invalid`() {
        val subscriptionMissingRequiredField = SubscriptionFactory().makeWithInvalidEmail()
        val stringifiedSubscription = mapper.writeValueAsString(subscriptionMissingRequiredField)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CONTROLLER_ROUTE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringifiedSubscription)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

        verify(mockSubscriptionJpaRepository, times(0)).save(subscriptionMissingRequiredField)
        verify(mockTransactionalEmailSending, times(0)).send(EmailMessage(
            content = "Yay",
            to = subscriptionMissingRequiredField.email,
            from = "marketing@iban.com",
            subject = "Subscription confirmed"
        ))
    }

    @Test
    fun `should return bad request 400 when given gender is invalid`() {
        val subscriptionMissingRequiredField = SubscriptionFactory().makeWithInvalidGender()
        val stringifiedSubscription = mapper.writeValueAsString(subscriptionMissingRequiredField)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CONTROLLER_ROUTE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringifiedSubscription)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

        verify(mockSubscriptionJpaRepository, times(0)).save(subscriptionMissingRequiredField)
        verify(mockTransactionalEmailSending, times(0)).send(EmailMessage(
            content = "Yay",
            to = subscriptionMissingRequiredField.email,
            from = "marketing@iban.com",
            subject = "Subscription confirmed"
        ))
    }

    @Test
    fun `should return bad request 400 when given date of birth is invalid`() {
        val subscriptionMissingRequiredField = SubscriptionFactory().makeWithInvalidDateOfBirth()
        val stringifiedSubscription = mapper.writeValueAsString(subscriptionMissingRequiredField)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CONTROLLER_ROUTE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringifiedSubscription)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

        verify(mockSubscriptionJpaRepository, times(0)).save(subscriptionMissingRequiredField)
        verify(mockTransactionalEmailSending, times(0)).send(EmailMessage(
            content = "Yay",
            to = subscriptionMissingRequiredField.email,
            from = "marketing@iban.com",
            subject = "Subscription confirmed"
        ))
    }

    @Test
    fun `should return bad request 400 when given newsletter id is invalid`() {
        val subscriptionMissingRequiredField = SubscriptionFactory().makeWithInvalidNewsletterId()
        val stringifiedSubscription = mapper.writeValueAsString(subscriptionMissingRequiredField)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CONTROLLER_ROUTE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringifiedSubscription)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

        verify(mockSubscriptionJpaRepository, times(0)).save(subscriptionMissingRequiredField)
        verify(mockTransactionalEmailSending, times(0)).send(EmailMessage(
            content = "Yay",
            to = subscriptionMissingRequiredField.email,
            from = "marketing@iban.com",
            subject = "Subscription confirmed"
        ))
    }
}