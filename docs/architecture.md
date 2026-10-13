# Architecture

## System overview

Cab Booking System is a single Spring Boot REST API backed by MySQL. It has no web or mobile frontend in this repository.

    HTTP client
       |
       v
    Spring MVC controllers
       |
       v
    Spring Security filter chain
       |-- public registration and login routes
       |-- JWT bearer filter for other requests
       |-- role checks on controller methods
       |
       v
    service implementations
       |
       v
    Spring Data JPA repositories
       |
       v
    MySQL database

The application runs on port **8080** by default.

## Main packages

| Package | Responsibility |
| --- | --- |
| **controller/** | Maps HTTP routes for users, drivers, rides, payments, and ratings. |
| **dto/** | Request payload objects for registration, login, booking, ratings, and payments. |
| **entity/** | JPA persistence models. |
| **enums/** | User roles, ride states, and payment methods. |
| **repository/** | Spring Data JPA repositories. |
| **service/** | Service interfaces and implementations for application operations. |
| **config/** | Spring Security filter chain and a currently commented OpenAPI configuration class. |
| **util/** | JWT generation/validation, bearer-token filter, entry point, and user lookup. |
| **exception/** | Custom exceptions and global response mapping. |

## Authentication flow

Registration and login endpoints are public. User login looks up an account by email and issues a JWT whose subject is the email. Driver login looks up by phone and issues a JWT whose subject is the phone. The token contains a role claim and is signed with the configured **jwt.secret** using HS256. Requests to other endpoints use an **Authorization: Bearer <token>** header; the filter reloads user details and places their role in Spring Security's context.

## Authorization behavior

The security configuration permits the user/driver registration and login routes and the OpenAPI/Swagger paths. Other requests require authentication. Method-level **@PreAuthorize** checks restrict access by roles such as **USER**, **DRIVER**, and **ADMIN**.

Role checks do not guarantee ownership of a specific object. Several methods accept a user, driver, ride, or payment ID without checking that it belongs to the authenticated principal. Review [API](api.md) and [Security](../SECURITY.md) before using real data.

## Domain model

- **User** stores name, email, unique phone, password hash, role, and creation time.
- **Driver** stores name, phone, license number, vehicle details, password hash, availability, and role.
- **Ride** relates to a user and driver and stores pickup/dropoff text, a double fare, and ride status.
- **Payment** relates to a ride and user and stores amount, method, status, and timestamp.
- **Rating** relates to a ride and records from/to IDs, score, and comments.

JPA creates or updates schema according to **spring.jpa.hibernate.ddl-auto**. The default is **update**. There are no versioned migration files in the repository.

## Important implementation limits

- Driver login logs the submitted password and stored hash.
- User profile and available-driver endpoints return entities directly, including sensitive fields.
- Ride fares are random demonstration values.
- Payment records are marked successful without a payment-provider call.
- Driver assignment takes the first available record.
- Database defaults, SQL logging, and security logging are development-only settings.
