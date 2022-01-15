package com.iban.subscriptionsapi.infrastructure.queue.redis

import com.iban.subscriptionsapi.application.protocols.QueueMessagePublisher
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisQueueMessagePublisher(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val channelTopic: ChannelTopic
) : QueueMessagePublisher {
    override fun publish(message: Any): Void? {
        redisTemplate.convertAndSend(channelTopic.topic, message)
        return null
    }
}