# 🗺️ Engineering Roadmap

Security and data integrity must precede product feature expansion. This document is a plan, not evidence that listed work has shipped.

## 🔴 P0: Critical exposure

- [ ] Remove driver login password/hash logging.
- [ ] Remove committed JWT signing secret and weak DB password defaults.
- [ ] Rotate secrets that may have been exposed.
- [ ] Replace entity responses with allow-listed DTOs.
- [ ] Enforce ownership for profile, driver status, ride status, and payment receipt.
- [ ] Add cross-account authorization tests.

**Exit:** no credential leakage, no sensitive entity fields in responses, ownership tests pass.

## 🟠 P1: Domain correctness

- [ ] Define and enforce ride status transitions.
- [ ] Replace random fare with a documented server-side model.
- [ ] Define cancellation and failure semantics.
- [ ] Make driver assignment transactional and concurrency-safe.
- [ ] Define payment idempotency and verification boundaries.
- [ ] Validate every request DTO.

**Exit:** deterministic rules, negative-path tests, transaction and concurrency coverage.

## 🟡 P2: Persistence and API quality

- [ ] Introduce Flyway or Liquibase migrations.
- [ ] Define stable response contracts.
- [ ] Remove stack-trace leakage and standardize error responses.
- [ ] Configure and verify OpenAPI bearer security.
- [ ] Add pagination and query limits where needed.

**Exit:** reproducible schema and API contract tests.

## 🟢 P3: Product capabilities

- [ ] Evaluate geospatial location support and privacy controls.
- [ ] Evaluate proximity-based driver matching.
- [ ] Define real-time ride status updates.
- [ ] Define notification requirements.
- [ ] Integrate payment provider only after security/compliance review.
- [ ] Define admin workflows and audit controls.

**Exit:** approved requirements, privacy review, threat model, tested implementation.

## 🔵 P4: Delivery and operations

- [ ] Select production hosting.
- [ ] Add reviewed CI and dependency update strategy.
- [ ] Define observability and alerting.
- [ ] Test backup and recovery.
- [ ] Document incident response and rollback.
- [ ] Add load and resilience tests.

Close a task only after implementation, review, and evidence. P0 work cannot be bypassed by feature work.
