package com.iban.subscriptionsapi.domain.models

data class EmailMessage(
    val content: String,
    val to: String,
    val from: String,
    val subject: String
)
