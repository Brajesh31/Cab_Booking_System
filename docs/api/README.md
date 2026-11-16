# 📡 REST API Reference

The canonical route inventory and behavior notes are documented in [../api.md](../api.md). This directory contains examples and a draft OpenAPI contract.

## Base URL

`http://localhost:8080`

## Authentication

Protected endpoints expect:

~~~http
Authorization: Bearer <JWT>
~~~

Rider login token subject is email; driver login token subject is phone.

## Endpoint groups

| Group | Prefix | Purpose |
|---|---|---|
| Rider | `/api/users` | Registration, login, profile |
| Driver | `/api/drivers` | Registration, login, availability |
| Ride | `/api/rides` | Booking, status, rider history |
| Payment | `/api/payments` | Record creation and receipt lookup |
| Rating | `/api/ratings` | Rating creation and target lookup |

See [examples](examples/README.md). The Postman collection uses placeholders only.

## Known API risks
- Several controllers return entities directly.
- Resource ownership checks are incomplete.
- Payment endpoint does not initiate a real payment.
- Swagger paths are permitted, but custom OpenAPI configuration is commented out.
