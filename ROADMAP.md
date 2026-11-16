# 🗺️ Roadmap

This roadmap describes possible improvements. It is not a delivery schedule or a claim that an item is implemented.

## Current baseline
The Java 21 / Spring Boot API has rider and driver registration/login, JWT authentication, basic ride assignment/status, ratings, and payment-record endpoints backed by JPA and MySQL. One context-load test exists. The project is a prototype and has material security and domain-flow gaps.

## 🔴 Priority 0: protect accounts and data
- [ ] Remove plaintext password and password-hash logging.
- [ ] Replace committed JWT signing default with a strong secret supplied through a secret manager.
- [ ] Remove hard-coded local DB credentials and use a least-privilege account.
- [ ] Return DTOs that never include password hashes or unrelated personal data.
- [ ] Enforce per-resource ownership for profiles, driver status, ride status, receipts, and ratings.
- [ ] Add input validation, consistent errors, and security-focused tests.

## 🟠 Priority 1: correct domain behavior
- [ ] Replace random ride fares with a documented pricing service and currency rules.
- [ ] Integrate an actual payment provider before describing payment as processed.
- [ ] Define and validate permitted ride status transitions.
- [ ] Assign drivers through a transactional matching workflow rather than selecting the first available record.
- [ ] Store timestamps as temporal types and design relationships/indexes deliberately.
- [ ] Add migration tooling and stop using schema auto-update for production.

## 🟡 Priority 2: operational readiness
- [ ] Replace DEBUG and SQL logging with reviewed profiles and redaction.
- [ ] Define token rotation/expiry and account lifecycle behavior.
- [ ] Add observability, health checks, backup/restore, and data-retention policies.
- [ ] Publish API schemas and role/ownership behavior that matches implementation.
- [ ] Expand unit, integration, and authorization coverage.

## Proposing work
Open an issue with user need, affected role/routes, acceptance criteria, migration impact, and validation plan. Security and payment correctness take priority over additional features.
