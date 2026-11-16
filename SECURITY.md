# 🔐 Security Policy

## Scope
This repository implements a prototype ride-booking API with JWT authentication and MySQL persistence. Reports about vulnerabilities, authorization failures, exposed credentials, or sensitive data handling are welcome.

## 🚨 Known security and deployment blockers
- **Committed defaults:** `application.properties` contains a local database username/password and a fixed JWT signing secret. They are not safe production values. Override locally and move secrets to a managed secret store before deployment.
- **Credential logging:** driver login prints the submitted password and stored password hash. These sensitive logs must be removed before use with real accounts.
- **Sensitive entity responses:** profile and available-driver endpoints return JPA entities directly. Those entities include password hash fields, and nested rides/payments may serialize related personal data.
- **Object ownership gaps:** profile, driver status, ride status, and payment receipt operations accept IDs without consistently verifying that the authenticated principal owns the resource. Role checks alone do not establish ownership.
- **Payment is simulated:** the payment service stores the requested amount and marks a record SUCCESS without contacting a payment processor.
- **Unsafe persistence/logging defaults:** Hibernate schema update, SQL output, and Spring Security DEBUG logging are enabled in checked-in configuration.

Do not deploy this revision with real accounts, driver licence details, precise ride locations, or payment data until these issues are addressed. See [Deployment](docs/deployment.md) and [Security Review](docs/SECURITY_REVIEW.md).

## 📨 Report a vulnerability
Do not publish unpatched vulnerability details, exploit steps, tokens, passwords, hashes, or personal data in a public issue. Use GitHub private vulnerability reporting if enabled: [Report a vulnerability](https://github.com/Brajesh31/Cab_Booking_System/security/advisories/new). If unavailable, contact the maintainer through [@brajesh31](https://github.com/brajesh31) and request a private channel.

Include affected endpoint/file, conditions needed to reproduce, likely impact, and suggested mitigation. Redact identifiers and secrets that are not essential to understanding the report.

## 🧭 Response
The maintainer will acknowledge a report when reasonably able, investigate the affected revision, and coordinate a fix and disclosure with the reporter. This individual project has no guaranteed response-time or service-level commitment.
