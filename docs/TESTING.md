# 🧪 Testing Strategy

## Current baseline

The inspected repository contains one test class: `src/test/java/com/cabbookingsystem/CabBookingSystemApplicationTests.java`. It is a Spring application context-load test. It does not prove endpoint correctness, role enforcement, object ownership, payment integrity, or concurrency safety.

## Run current tests

macOS/Linux:

~~~bash
./mvnw test
~~~

Windows:

~~~powershell
.\mvnw.cmd test
~~~

Package:

~~~bash
./mvnw clean package
~~~

These are instructions, not test results. Do not claim they passed unless actually executed.

## Test layers to build

| Layer | Coverage | Suggested tooling |
|---|---|---|
| Unit | Service rules, state transitions, token helpers | JUnit 5, Mockito |
| MVC | Route mapping, validation, JSON shape, status codes | MockMvc, @WebMvcTest |
| Security | JWT parsing, role restrictions, anonymous requests | spring-security-test |
| Repository | Query behavior and mappings | @DataJpaTest |
| Integration | Full API-to-database flows | @SpringBootTest, Testcontainers |
| Contract | API request/response compatibility | OpenAPI validation |
| Concurrency | Driver assignment and availability races | Concurrent integration tests |

## Critical scenarios

### Authentication and authorization
- Missing, invalid, expired, malformed, and wrong-signature JWTs are rejected.
- Rider cannot call driver-only operations.
- Driver cannot access another rider's records.
- Driver cannot update a ride assigned to another driver.
- Rider cannot read another rider's payment receipt.
- Admin behavior is explicit and audited.

### Accounts and privacy
- Duplicate email/phone registration is rejected.
- Passwords are never logged.
- Password hashes never appear in responses.
- Driver licence fields are minimized and restricted.
- Login errors do not disclose account existence.

### Ride lifecycle
- Booking fails when no driver is available.
- One driver cannot be allocated to multiple active rides.
- Invalid status values return a controlled client error.
- Disallowed transitions are rejected.
- Only the assigned driver/admin may update a ride.
- Rider history is scoped to the authenticated rider.

### Payment boundary
- Client-supplied amount is not blindly trusted.
- Retries are idempotent.
- SUCCESS requires provider confirmation in a real integration.
- Receipt access is scoped to owner/admin.
- Future provider webhooks validate signatures.

### Ratings
- Score range is validated.
- Rating relates to a valid ride.
- Duplicate rating policy is explicit.
- Caller cannot spoof fromUserId.
- Results do not expose unrelated data.

## Test database

Use a separate database or ephemeral Testcontainers instance. Never run tests against real rider/driver records. Do not use Hibernate auto-update as a migration test.

## Evidence

Record commit SHA, JDK/Maven versions, exact command, database version, test count, pass/fail result, sanitized logs, and known skips. Do not fabricate coverage or scan results.

## Completion standard

Changes affecting authentication, ownership, payment, or dispatch need negative and concurrency tests, not only successful compilation.
