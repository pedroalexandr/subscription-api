package com.iban.subscriptionsapi.application.protocols

interface QueueMessagePublisher {
    fun publish(message: Any): Void?
}