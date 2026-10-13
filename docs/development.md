# Development

## Project workflow

This is a Java 21 Spring Boot REST API with a Maven Wrapper. Start MySQL locally, export datasource and JWT variables from **.env.example**, and use **./mvnw spring-boot:run** (or **mvnw.cmd spring-boot:run** on Windows). No frontend is included.

## Where to make changes

- Add or update HTTP mappings in **controller/**.
- Define request and response payloads in **dto/**. Current controllers often return JPA entities directly; prefer response DTOs that omit password hashes and unrelated personal data.
- Put persistence fields and relationships in **entity/**.
- Define repository operations in **repository/**.
- Keep business logic in **service/** and **service/impl/**.
- Configure authentication and infrastructure in **config/** and **util/**.
- Update **application.properties** and environment documentation when configuration changes.
- Use a versioned migration when changing schema; the repository currently has no migration framework configured.

## Authentication and authorization

Public routes are limited by **SecurityConfig** to registration/login and the OpenAPI/Swagger paths. Other requests require JWT authentication, and several controllers apply role checks with **@PreAuthorize**.

For every record operation, check both role and ownership from the authenticated principal. A user-controlled ID is not proof of ownership. Current driver status, ride status, payment receipt, and profile operations have gaps that should be addressed before expanding their use.

## Sensitive data and security

Password hashes are created with BCrypt, but driver login currently prints both submitted passwords and stored hashes. Remove those log statements. The project also uses an in-source JWT signing default, returns entity objects containing hash fields, and relies on MySQL schema auto-update. Do not log tokens, passwords, hashes, license data, or precise pickup/dropoff details.

The payment service records a caller-supplied amount as **SUCCESS** without a payment gateway. Do not describe this as a processed payment. Ride price generation is a random placeholder.

## Verification

A single Spring context-load test is present. No endpoint, authorization, validation, or domain tests are currently documented. For changes, run the relevant Maven checks and add tests for role boundaries and object ownership. State which checks were actually performed.
