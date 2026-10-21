# Cab Booking System API

This repository contains a Java 21 / Spring Boot REST API prototype for rider and driver registration, JWT login, ride booking/status, driver availability, ratings, and payment records. It uses Spring Data JPA with MySQL and Spring Security.

## Important security and functionality notes

This is a prototype, not a production-ready ride or payment service. The committed application properties contain weak local database credentials and a fixed JWT signing secret. Driver login prints the submitted password and stored password hash to standard output. Several controllers return JPA entities directly, including fields such as password hashes. Some record-ID operations do not check that the authenticated user owns the referenced ride, driver, payment, or profile.

The payment service does not call a payment provider; it stores a record with status **SUCCESS**. Ride fare is currently generated with a random value, not a pricing model. Do not deploy this API with real accounts, location data, driver license information, or payment details until the security and domain limitations are addressed. See [Security](SECURITY.md) and [Deployment](docs/deployment.md).

## Technology

- Java 21 and Spring Boot 3.5.
- Spring Web, Spring Security, Spring Data JPA, Hibernate Validator.
- MySQL Connector/J.
- JWT bearer authentication using JJWT 0.11.5.
- Springdoc OpenAPI UI dependency.
- Maven Wrapper for build and test commands.

## Repository structure

- **src/main/java/com/cabbookingsystem/controller/** — REST routes for users, drivers, rides, payments, and ratings.
- **entity/** — JPA entities and relationship fields.
- **dto/** — request objects.
- **service/** and **service/impl/** — application logic.
- **repository/** — Spring Data JPA repositories.
- **config/** — Spring Security and OpenAPI configuration.
- **util/** — JWT creation, validation, and authentication filter.
- **src/main/resources/application.properties** — server, MySQL, JPA, and JWT configuration.
- **src/test/** — one Spring context-load test.
- **pom.xml** — Java version and Maven dependencies.

## API overview

Base URL for local development: **http://localhost:8080**. Authenticated endpoints expect a JWT in the **Authorization: Bearer <token>** header.

| Method and path | Access configured in code | Purpose |
| --- | --- | --- |
| **POST /api/users/register** | Public | Register a rider account. |
| **POST /api/users/login** | Public | Authenticate a rider and return a JWT. |
| **GET /api/users/profile/{userId}** | USER or ADMIN | Read a user profile by ID. Current implementation does not enforce owner-only access and returns the entity directly. |
| **POST /api/drivers/register** | Public | Register a driver account. |
| **POST /api/drivers/login** | Public | Authenticate a driver and return a JWT. Current service logs credentials and hashes. |
| **GET /api/drivers/available** | USER or ADMIN | List available drivers. Current response returns JPA entities. |
| **PUT /api/drivers/status/{id}?available=true** | DRIVER or ADMIN | Change a driver's availability; ownership is not checked. |
| **POST /api/rides/book** | USER or ADMIN | Create a ride and assign the first available driver. |
| **PUT /api/rides/status/{id}?status=ONGOING** | DRIVER or ADMIN | Change a ride status; driver-to-ride ownership is not checked. |
| **GET /api/rides/user/rides** | USER or ADMIN | List rides for the authenticated rider. |
| **POST /api/payments/process** | USER or ADMIN | Store a payment record; this does not charge a payment method. |
| **GET /api/payments/receipt/{rideId}** | USER or ADMIN | Read a payment by ride ID; owner access is not checked in the service. |
| **POST /api/ratings** | USER, DRIVER, or ADMIN | Submit a rating for a ride. |
| **GET /api/ratings/user/{toUserId}** | DRIVER | List ratings by target ID. |

Springdoc endpoints are permitted by the security configuration. See [API documentation](docs/api.md) for request fields and current limitations.

## Local setup

Requirements: JDK 21, MySQL, and the included Maven Wrapper. Create a local database, then supply datasource and JWT values through environment variables. The root **.env.example** documents variable names; Spring Boot does not automatically load that file.

Windows PowerShell example:

    $env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/cab_booking_db"
    $env:SPRING_DATASOURCE_USERNAME = "your_local_db_user"
    $env:SPRING_DATASOURCE_PASSWORD = "your_local_db_password"
    $env:JWT_SECRET = "replace-with-a-random-secret-of-at-least-32-bytes"
    .\mvnw.cmd spring-boot:run

On macOS or Linux, run **./mvnw spring-boot:run** after exporting the same environment variables. Open **http://localhost:8080** for the API host; endpoints are under **/api/**.

Hibernate is configured with **ddl-auto=update**. Review [Database](docs/database.md) before starting against any database with valuable data.

## Maven commands

Run from the repository root:

- **./mvnw test** — run the configured Maven test suite.
- **./mvnw package** — compile and package the application.
- **./mvnw spring-boot:run** — launch the development server.

On Windows, use **mvnw.cmd**. This documentation update did not run builds or tests. The current test source contains only a context-load test and does not cover API authorization or business behavior.

## Documentation

- [Architecture](docs/architecture.md)
- [Installation](docs/installation.md)
- [Development](docs/development.md)
- [Deployment](docs/deployment.md)
- [API](docs/api.md)
- [Database](docs/database.md)
- [Troubleshooting](docs/troubleshooting.md)
- [FAQ](docs/faq.md)
- [Contributing](CONTRIBUTING.md)
- [Security policy](SECURITY.md)
- [Support](SUPPORT.md)
- [Roadmap](ROADMAP.md)

## License

Original project code and documentation are licensed under MIT; see [LICENSE](LICENSE). Third-party dependencies retain their own licenses.

## Maintainer

Brajesh Kumar — GitHub [@brajesh31](https://github.com/brajesh31).

Last updated: 2026-10-21 15:42 UTC
