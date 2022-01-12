# Subscription API

## Pre-requisites
- Docker and docker-compose

## Build and run the solution
``$ docker-compose up -d``

## Shutdown the infrastructure
``$ docker-compose down``

## Usage
Essentially, you'll have to build and run the solution and use some sort of
HTTP client to make a POST request to the ``/subscriptions`` endpoint 
according to **this** specification.

## Tech stack
- **Oracle** JDK 11
- Docker and docker-compose for managing the required infrastructure
- Spring Boot v2.6.2
- Maven as the build automation tool
- Spring MVC for creating RESTful application more easily
- Spring Data JPA for data persistence
- H2 Database (in-memory database)
- Swagger for API documentation
- Mailhog for SMTP server