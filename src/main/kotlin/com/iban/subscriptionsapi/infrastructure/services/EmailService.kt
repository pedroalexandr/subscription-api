package com.iban.subscriptionsapi.infrastructure.services

interface EmailService {
    fun sendSimpleMessage(to: String, subject: String, text: String)
}