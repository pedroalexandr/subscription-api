package com.iban.subscriptionsapi.infrastructure.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.SimpleMailMessage

@Component
class EmailServiceImpl(private val emailSender: JavaMailSender) : EmailService {
    @Value("\${spring.mail.username}")
    lateinit var sender: String

    override fun sendSimpleMessage(to: String, subject: String, text: String) {
        val message = SimpleMailMessage()

        message.setTo(to)
        message.setFrom(sender)
        message.setSubject(subject)
        message.setText(text)

        emailSender.send(message)
    }
}