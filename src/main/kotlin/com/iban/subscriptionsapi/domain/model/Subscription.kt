package com.iban.subscriptionsapi.domain.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "subscriptions")
data class Subscription(
    @Id @GeneratedValue
    val id: Long,
    val email: String,
    val firstName: String = "",
    val gender: String = "",
    val dateOfBirth: String,
    val optIn: Boolean,
    val newsletterID: Long,
)