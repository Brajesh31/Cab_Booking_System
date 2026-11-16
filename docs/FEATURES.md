# 🧩 Feature Inventory and Capability Boundaries

This inventory separates source-confirmed behavior from simplifications and missing functionality.

## Implemented in current source

| Area | Source locations | Current behavior |
|---|---|---|
| Rider registration/login | UserController, UserServiceImpl | Registration and login |
| Driver registration/login | DriverController, DriverServiceImpl | Registration and login |
| JWT authentication | JwtAuthenticationFilter, JwtUtil, CustomUserDetailsService | Parse bearer token and load principal |
| Role checks | SecurityConfig and controller annotations | Selected USER/DRIVER/ADMIN restrictions |
| Driver availability | DriverController, DriverServiceImpl | List and update availability |
| Ride booking | RideController, RideServiceImpl | Select first available driver and persist ride |
| Ride status | RideController, RideServiceImpl, RideStatus | Update enum state |
| Rider history | RideController, RideRepository | Fetch current rider's rides |
| Payment records | PaymentController, PaymentServiceImpl | Persist payment metadata and status |
| Ratings | RatingController, RatingServiceImpl | Create/query ratings |
| Exception handling | GlobalExceptionHandler | Map selected exceptions to HTTP responses |
| API documentation dependency | pom.xml | Springdoc dependency |

## Implemented but not production-grade

- Booking selects the first available driver, not the nearest/best match.
- Driver availability has no documented transactional locking strategy.
- Fare is a random demonstration value.
- Payment is marked SUCCESS without a provider call.
- Ride status does not enforce a transition matrix.
- Some controllers return entities directly.
- ID-based operations do not consistently enforce ownership.
- Driver login logs secrets.
- Hibernate schema auto-update is enabled.

## Not present in repository

- Web/mobile client.
- GPS/maps/geocoding/live tracking.
- Distance/time fare model or surge pricing.
- Actual card/UPI/wallet integration or webhook verification.
- Refund, payout, or reconciliation workflow.
- Notification integrations.
- Queue, event bus, cache, or service discovery.
- Database migration framework.
- Browser or mobile E2E tests.
- Production hosting, monitoring, backup automation.

## Verification status

The current test source contains one Spring application context-load test. It does not prove endpoint correctness. Add controller/service tests and integration tests against a disposable database, including negative authorization and ownership cases.

## Updating this document

For every new feature, document the user problem, route, DTO, service rule, failure path, schema changes, authorization/ownership, tests, privacy implications, and operational requirements. Do not call roadmap proposals implemented until code and tests support the claim.
