package com.iban.subscriptionsapi.presentation.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.github.javafaker.Faker
import com.iban.subscriptionsapi.domain.models.Subscription
import com.iban.subscriptionsapi.infrastructure.db.SubscriptionRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate

const val CONTROLLER_ROUTE = "/api/v1/subscriptions"

@SpringBootTest
@AutoConfigureMockMvc
class SubscriptionsControllerTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var subscriptionRepository: SubscriptionRepository

    private val mapper = ObjectMapper()
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    private val faker = Faker()

    @Test
    fun `should create a subscription successfully`() {
        val subscription = Subscription(
            email = faker.internet().emailAddress(),
            firstName = faker.name().firstName(),
            dateOfBirth = LocalDate.now(),
            optIn = true,
            newsletterId = 1
        )

        val stringifiedSubscription = mapper.writeValueAsString(subscription)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CONTROLLER_ROUTE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringifiedSubscription)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.email").value(subscription.email))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.firstName").value(subscription.firstName))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.dateOfBirth").value(subscription.dateOfBirth.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.optIn").value(subscription.optIn))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.newsletterId").value(subscription.newsletterId))

        Assertions.assertFalse(subscriptionRepository.findAll().isEmpty())
    }

    @Test
    fun `should return bad request status when any required parameter is not provided`() {
        val subscriptionMissingEmailField = object {
            val firstName = faker.name().firstName()
            val dateOfBirth = LocalDate.now()
            val optIn = true
            val newsletterId = 1
        }

        val stringifiedSubscription = mapper.writeValueAsString(subscriptionMissingEmailField)

        mockMvc.perform(
            MockMvcRequestBuilders.post(CONTROLLER_ROUTE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(stringifiedSubscription)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

        Assertions.assertTrue(subscriptionRepository.findAll().isEmpty())
    }
}