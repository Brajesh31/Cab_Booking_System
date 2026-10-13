# Security Policy

## Scope

This repository implements a prototype ride-booking API with JWT authentication and MySQL persistence. Reports about vulnerabilities, authorization failures, exposed credentials, or sensitive data handling are welcome.

## Known security and deployment blockers

- **Committed defaults:** **application.properties** contains a local database username/password and a fixed JWT signing secret. They are not safe production values. Override them locally and move secrets to a secret manager before deployment.
- **Credential logging:** the driver login service prints the submitted password and stored password hash. These sensitive logs must be removed before use with real accounts.
- **Sensitive entity responses:** profile and available-driver endpoints return JPA entities directly. Those entities include password hash fields, and ride/payment entities may serialize related user or driver fields as well.
- **Object ownership gaps:** profile, driver status, ride status, and payment receipt operations accept IDs without consistently verifying the authenticated principal owns the resource. Role checks alone do not establish ownership.
- **Payment is simulated:** the payment service stores the requested amount and marks a record **SUCCESS** without contacting a payment processor.
- **Unsafe persistence defaults:** Hibernate schema update, SQL output, and Spring Security DEBUG logging are enabled in the checked-in configuration.

Do not deploy this revision with real accounts, driver license details, precise ride locations, or payment data until these issues are addressed. See [Deployment](docs/deployment.md).

## Report a vulnerability

Please do not publish unpatched vulnerability details, exploit steps, tokens, passwords, or personal data in a public issue. Use GitHub's private vulnerability reporting for this repository if enabled: [Report a vulnerability](https://github.com/Brajesh31/Cab_Booking_System/security/advisories/new). If unavailable, contact the maintainer through [@brajesh31](https://github.com/brajesh31) and request a private channel.

Include the affected endpoint or file, conditions needed to reproduce the issue, likely impact, and suggested mitigation. Redact all identifiers and secrets that are not necessary to understand the report.

## Response

The maintainer will acknowledge a report when reasonably able, investigate the affected revision, and coordinate a fix and disclosure with the reporter. This individual project has no guaranteed response-time or service-level commitment.
