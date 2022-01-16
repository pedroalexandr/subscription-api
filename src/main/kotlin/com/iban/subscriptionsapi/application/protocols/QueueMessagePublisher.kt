package com.iban.subscriptionsapi.application.protocols

interface QueueMessagePublisher {
    fun publish(key: String, message: Any): Void?
}