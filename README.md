# TravelConnect

## Description
TravelConnect is a microservices-based hotel management and review platform designed to provide a secure, seamless booking and review experience. The project leverages a suite of services to manage user accounts, hotel listings, and user reviews, while also incorporating Okta authentication to secure access to APIs. The system uses Eureka for service discovery, an API Gateway for routing, and a centralized Config Server for configuration management.

### Microservices Overview
1. **user-service**: Manages user profiles, authentication, and authorization.
2. **hotel-service**: Manages hotel information, including listings, availability, and details.
3. **rating-service**: Manages hotel ratings and reviews, allowing users to provide feedback.
4. **server-registry**: Eureka-based service registry for dynamic discovery of microservices.
5. **api-gateway**: Routes external client requests to the appropriate services securely.
6. **config-server**: Centralized configuration server to manage application configurations.
7. **security (Okta)**: Provides secure authentication and authorization through Okta's OAuth 2.0 integration.

### Key Features
- **User Authentication with Okta**: Integrates Okta's OAuth 2.0 for secure authentication and role-based access control.
- **Hotel Listings**: Enables management of hotel details, including adding, updating, and retrieving information.
- **Ratings & Reviews**: Allows users to submit reviews and ratings for hotels.
- **Service Discovery**: Uses Eureka for registering and discovering services dynamically.
- **API Gateway**: Acts as a single entry point, routing requests securely to the appropriate services.
- **Centralized Configuration**: Manages configurations through a dedicated config server for consistent setup across services.

### Technologies Used
- **Spring Boot**: For building each microservice.
- **Spring Cloud Eureka**: Service registry for managing microservices.
- **Spring Cloud Gateway**: API gateway for routing and securing requests.
- **Spring Cloud Config**: Centralized configuration management.
- **Okta**: For secure OAuth 2.0 authentication and authorization.
- **MySQL**: Database for storing user, hotel, and review information.
- **REST API**: Enables inter-service communication.

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- MySQL database


