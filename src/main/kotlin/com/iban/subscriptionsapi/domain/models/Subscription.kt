package com.iban.subscriptionsapi.domain.models

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.*

@Entity(name = "subscriptions")
class Subscription(
    @Id @GeneratedValue
    private val id: Long? = null,

    @field:Email(message = "Invalid email")
    @field:NotBlank(message = "Email is required")
    val email: String,

    val firstName: String? = null,

    @field:Pattern(regexp = "[MFO]", message = "Invalid gender. Must be one of the following: M, F or O")
    val gender: String? = null,

    @field:Past(message = "The date of birth must be in the past")
    @field:NotNull(message = "Date of birth is required")
    val dateOfBirth: LocalDate,

    val optIn: Boolean,

    @field:NotNull(message = "A newsletter related is required")
    @field:Min(1, message = "Invalid id for related newsletter")
    val newsletterId: Long,
)
