# Subscription API

## Reading guidance

- `{api_domain}`: change it for the actual public domain you are accessing the API
- `{amount_of_workers}`: change it for the amount of workers you want to use (e.g. 3)

## Pre-requisites

- `Docker 18.06.0+`
- `docker-compose 1.29.2`

## Build and run the solution

`$ docker-compose up -d`

## OPTIONAL: Build and run the solution scaling the email service

`$ docker-compose up -d --scale email-service-worker={amount_of_workers}`

## Check the application status

Use any sort of HTTP client to perform a `GET` request to the `/actuator/health` endpoint.

Example:  
`$ curl --location --request GET 'http://{api_domain}/actuator/health'`

## Shutdown the infrastructure

`$ docker-compose down -v`

## Usage

After successfully [building and running](#build-and-run-the-solution) the solution, you can use any sort of HTTP client to perform a `POST` request to the `api/v1/subscriptions` endpoint accordingly to its [specification](http://{api_domain}/api/v1/swagger-ui/index.html). You can do that more easily using Swagger's UI by performing the following steps:

1. Go in [here](http://{api_domain}/api/v1/swagger-ui/index.html#/subscriptions-controller/create) and hit the **Try it out** button
2. Edit the **request body** as you want
3. Hit the **Execute** button
4. Look at the **Responses** section below.

After successfully creating a subscription, you can go to [Mailhog's UI](http://{api_domain}:8025) to check the email messages that have been sent, open them and check their properties (subject, recipient, sender and content). The application is using `Mailhog` as a fake SMTP server so **no email messages are actually sent**.

## Development-related technologies used

### Spring Boot 2.6.2 + Kotlin

Preferred technologies for the coding challenge

### Python 3.6 (libs: redis, json, smtplib, email.message)

Used to run the python script that handles the email sending microservice

### Spring Web dependency

Used to create RESTful applications more easily and have the heavy-lifting taken care of for me

### JavaFaker dependency

Used mostly in helper functions and tests to help generate random fake data

### Gson dependency

Used to convert the email message object into a JSON string to be published to the queue

### Actuator dependency

Used to have endpoints to check the API health, metrics, sessions etc.

### Javax Validation dependency

Used to validate the domain models and all entities/components that need field or group-specific validation.

### Jedis 3.6.2 dependency

Used as a Redis client to publish messages more easily on the Redis queue service

### PostgreSQL dependency

Used to connect and interact with the PostgreSQL database.

### Spring Data JPA

Used for data persistence especially because it makes it pretty trivial to implement CRUD operations

### OpenAPI + Swagger

Used to document the API because it makes it pretty trivial to get a fully-fledged interactive API documentation

## Infrastructure-related technologies used

### PostgreSQL 13.1

Used as the relational database for storing the subscriptions

### Mailhog for SMTP

Used as a fake local SMTP server that provides a UI to verify the email messages

### Nginx 1.13

Used with the sole purpose of being a reverse proxy for the API. It is reliable, easy to configure and get things working properly.

### Redis 3.2

Used to serve as a queue that stores the produced email messages which are consumed by the email service
