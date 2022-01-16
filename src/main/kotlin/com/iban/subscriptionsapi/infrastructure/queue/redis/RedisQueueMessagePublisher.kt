package com.iban.subscriptionsapi.infrastructure.queue.redis

import com.google.gson.Gson
import com.iban.subscriptionsapi.application.protocols.QueueMessagePublisher
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import redis.clients.jedis.Jedis

@Component
class RedisQueueMessagePublisher : QueueMessagePublisher {
    @Value("\${spring.redis.host}")
    private lateinit var redisHost: String

    @Value("\${spring.redis.port}")
    private lateinit var redisPort: String

    override fun publish(key: String, message: Any): Void? {
        val jedis = Jedis(redisHost, redisPort.toInt())
        jedis.rpush(key, Gson().toJson(message))
        return null
    }
}