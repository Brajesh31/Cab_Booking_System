# 🔐 Authentication, Authorization, and Data Protection

## Authentication design

The project uses stateless JWT authentication. Rider login issues a token whose subject is the rider email. Driver login uses the driver's phone number as subject. A role claim is converted into Spring Security authorities by the JWT filter and user-details service.

Protected requests use:

~~~http
Authorization: Bearer <token>
~~~

## Public routes

The current SecurityConfig permits:
- POST /api/users/register
- POST /api/users/login
- POST /api/drivers/register
- POST /api/drivers/login
- /v3/api-docs/**
- /swagger-ui/**
- /swagger-ui.html

Other requests require authentication. Method-level annotations impose selected USER, DRIVER, and ADMIN roles.

## Role is not ownership

An authenticated USER role does not prove that a user owns the requested record. The current code has ID-based operations without consistent resource ownership checks.

~~~text
Trusted authenticated principal
          |
          v
Resolve identity from SecurityContext
          |
          v
Load resource by ID AND owner/assignee
          |
          v
No matching resource? Return controlled 404/403
          |
          v
Perform permitted operation
~~~

Never trust a client-supplied owner ID as proof of ownership. Admin overrides should be explicit and auditable.

## Credential protection

- Store passwords using a strong adaptive hash.
- Never log passwords, password hashes, JWTs, or signing secrets.
- Remove current driver-login credential log statements.
- Remove committed JWT/database secret defaults and rotate them.
- Use separate least-privilege database identities per environment.

## API response minimization

Do not return JPA User or Driver entities directly. Define response DTOs that contain only fields required by the caller. Exclude hashes, licence identifiers, internal roles when unnecessary, and unrelated relationship data.

## Payment boundary

The current payment service creates a record and assigns a success status without invoking a payment provider. It is not an actual charge, authorization, settlement, refund, or verified receipt.

A future payment integration requires server-side amount calculation, provider credentials, signed webhook validation, idempotency, reconciliation, and explicit pending/success/failure states.

## Location privacy

Pickup and drop-off values may reveal sensitive location information. Minimize retention, access, and logging. Define privacy and deletion rules before collecting real data.

## Production security gate

The current source does not pass a production security gate. See [Security review](SECURITY_REVIEW.md). This guide is not a security certification.
