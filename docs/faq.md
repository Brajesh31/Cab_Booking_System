# Frequently asked questions

## Is there a frontend in this repository?

No. This repository contains a Spring Boot REST API only. Use an HTTP client or build a separate frontend.

## What Java version and database does it use?

The Maven project targets Java 21 and uses MySQL through Spring Data JPA/Hibernate. The server listens on port 8080 by default.

## How do authentication tokens work?

User login returns a JWT with the user's email as subject; driver login returns a JWT with the driver's phone as subject. Send the token in an **Authorization: Bearer** header. The token includes a role claim.

## Which routes are public?

User and driver registration/login, plus the configured Swagger/OpenAPI paths. Other requests require authentication and controller role checks.

## Are all protected records restricted to their owner?

No. Several methods accept arbitrary IDs and check only the user's role. Profile, driver status, ride status, and payment receipt operations do not consistently verify ownership. Do not use this revision with real records.

## Does the payment endpoint charge a card or UPI account?

No. It saves a payment record with the caller-provided amount and a **SUCCESS** status. There is no payment-provider integration.

## Why should driver login logs be treated as sensitive?

The current implementation prints the submitted password and stored password hash. Remove those statements before handling real accounts and treat existing logs as sensitive.

## Does this project include database migrations?

No. Hibernate is configured to update the schema automatically, and there are no versioned migration files. This is unsuitable as a production migration process.

## Is the project production-ready?

No. The JWT default, database defaults, sensitive logs, entity responses, authorization gaps, and simulated payment behavior must be corrected first. See [Deployment](deployment.md) and [Security](../SECURITY.md).

## Where is the license and maintainer information?

Original project code and docs are under the MIT License, copyright Brajesh Kumar. See [LICENSE](../LICENSE) and [Support](../SUPPORT.md).
