# 🚨 Security Review and Remediation Register

## Executive summary

The current application must be treated as an educational prototype. Source inspection identified multiple high-impact issues. Do not connect it to real rider/driver data or process real payments until critical findings are fixed and verified.

This is source inspection, not a penetration test or formal security audit.

## 🔴 SEC-01: Driver login logs passwords and hashes

**Evidence:** `src/main/java/com/cabbookingsystem/service/impl/DriverServiceImpl.java`

The login implementation prints submitted credentials and stored password hash data.

**Remediation:** Remove all logging of passwords, hashes, tokens, licence data, and personal data. Restrict historical log access and rotate affected credentials if logs may have been exposed.

## 🔴 SEC-02: Fixed JWT signing secret

**Evidence:** `src/main/resources/application.properties`

A fixed signing secret is committed in source. Anyone with repository access can derive valid signatures for the configured environment.

**Remediation:** Remove the hard-coded secret, require an environment-provided/managed secret, fail startup on weak/missing production values, rotate the key, and invalidate previously issued tokens.

## 🔴 SEC-03: Weak database credential defaults

**Evidence:** `src/main/resources/application.properties`

The properties include root/root database credentials.

**Remediation:** Remove password defaults, use a least-privilege database user, externalize secrets, and rotate any credential reused beyond a disposable local database.

## 🔴 SEC-04: Persistence entities returned from controllers

User and Driver endpoints serialize entity objects directly. Those entities contain password hash and identity fields. Nested JPA relationships can also expose unintended data.

**Remediation:** Introduce allow-listed response DTOs and contract tests proving sensitive fields never appear.

## 🔴 SEC-05: Incomplete resource ownership authorization

Profile, driver status, ride status, and payment receipt operations use IDs without consistent ownership verification.

**Remediation:** Resolve the caller from SecurityContext, query records by both ID and owner/assignee, make admin override explicit/audited, and add cross-account negative tests.

## 🟠 Additional concerns

- Random demonstration fare, not a pricing model.
- Payment record marked SUCCESS without provider verification.
- Ride state update does not enforce allowed transitions.
- Driver selection has no concurrency-safe reservation strategy.
- Hibernate schema auto-update without migration history.
- Validation annotations are not consistent across request DTOs.
- Global exception handling prints stack traces; generic errors may expose internals.
- Custom OpenAPI configuration is commented out.

## Remediation priority

1. Stop credential logging.
2. Externalize and rotate JWT/database credentials.
3. Replace entity responses with DTOs.
4. Add record ownership authorization.
5. Validate request data and ride transitions.
6. Build real payment boundaries only with a reviewed provider integration.
7. Introduce migrations and transactional dispatch.
8. Add security, integration, and concurrency tests.

## Verification

Close a finding only after code is merged, negative tests pass, configuration/logs are rechecked, required rotation is complete, and related documentation is updated. Documentation alone does not remediate a vulnerability.
