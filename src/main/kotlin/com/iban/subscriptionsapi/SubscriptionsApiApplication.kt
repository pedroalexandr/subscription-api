package com.iban.subscriptionsapi

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@OpenAPIDefinition(
	servers = [Server(url = "/")]
)
@SpringBootApplication
class SubscriptionsApiApplication

fun main(args: Array<String>) {
	runApplication<SubscriptionsApiApplication>(*args)
}
