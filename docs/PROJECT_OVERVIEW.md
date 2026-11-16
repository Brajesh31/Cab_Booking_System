# 🚕 Project Overview

## What this project is

Cab Booking System is a Java 21 / Spring Boot 3.5 REST API prototype. It supports rider and driver account endpoints, JWT authentication, ride booking and status updates, payment-record persistence, and ratings.

It is a backend-only repository. No browser UI, mobile app, dispatch map, geolocation provider, payment gateway, notification provider, or production operations stack is included in the inspected source.

## Current capabilities

| Capability | Current implementation |
|---|---|
| Rider registration/login | User controller and service |
| Driver registration/login | Driver controller and service |
| JWT security | Stateless JWT filter and Spring Security |
| Driver availability | List and update boolean availability |
| Ride booking | Assigns first available driver and persists ride |
| Ride status | Updates enum from request parameter |
| Rider ride history | Retrieves rides associated with authenticated rider |
| Payment record | Persists supplied amount/method and status |
| Ratings | Stores ride rating and supports target-based lookup |

## Not present

- Frontend or mobile application.
- GPS, maps, geocoding, route optimization, or live tracking.
- Proximity-based driver matching.
- Real fare calculation.
- Payment processor, refund, webhook, or reconciliation.
- SMS/email/push notification integration.
- Versioned database migrations.
- Full API, authorization, integration, or concurrency test coverage.
- Configured production hosting, monitoring, and backup automation.

## Main personas

**Rider:** registers, authenticates, books a ride, retrieves associated rides, and may submit ratings within current route rules.

**Driver:** registers, authenticates, appears as available, and updates ride status within current route rules.

**Administrator:** appears as a role constant and is permitted by selected annotations. A complete admin-management workflow is not present.

## Domain vocabulary

- **Ride:** trip record with rider, assigned driver, pickup/drop-off strings, fare, and status.
- **Driver availability:** boolean state used to find a driver.
- **Payment record:** metadata associated with a ride and user, not proof of money movement.
- **Rating:** score and optional comment associated with a ride and user identifiers.
- **JWT:** signed bearer token representing the authenticated principal and role.

## Production-grade success criteria

A production version needs tested role and ownership rules, safe response DTOs, secure secret handling, credential-safe logging, explicit pricing and payment boundaries, concurrency-safe dispatch, database migrations, auditability, privacy controls, monitoring, and recovery procedures.

See [Security review](SECURITY_REVIEW.md), [Testing](TESTING.md), and [Roadmap](../ROADMAP.md).
