# Roadmap

This roadmap describes possible improvements. It is not a delivery schedule or a claim that an item is already implemented.

## Current baseline

The Java 21 / Spring Boot API has rider and driver registration/login, JWT bearer authentication, basic ride assignment/status, ratings, and payment-record endpoints backed by JPA and MySQL. A single context-load test exists. The project is a prototype and has significant security and business-flow gaps.

## Priority 1: protect accounts and data

- Remove plaintext password and password-hash logging.
- Replace the committed JWT signing default with a generated secret supplied only by a secret manager.
- Remove hard-coded local database credentials and provide a least-privilege account.
- Return DTOs that never include password hashes or unrelated personal data.
- Enforce per-resource ownership for profiles, driver status, ride status, receipts, and ratings.
- Add input validation, consistent error mapping, and security-focused tests.

## Priority 2: correct domain behavior

- Replace random ride fares with a documented pricing service and currency rules.
- Integrate an actual payment provider before describing payment as processed.
- Define and validate permitted ride status transitions.
- Assign drivers through a transactional matching workflow rather than selecting the first available record.
- Store timestamps as appropriate temporal types and design entity relationships and indexes deliberately.
- Add migration tooling and stop using schema auto-update for production.

## Priority 3: operational readiness

- Replace DEBUG and SQL logging with reviewed profiles and redaction.
- Define token rotation/expiry and account lifecycle behavior.
- Add observability, health checks, backup/restore, and data-retention policies.
- Publish API schemas and role/ownership behavior that matches the implementation.
- Expand unit, integration, and authorization coverage.

## Proposing work

Open an issue with the user need, affected role and routes, acceptance criteria, migration impact, and validation plan. Security and payment correctness take priority over additional features.
