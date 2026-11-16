# 🧭 Operations Guide

## Runtime model

The current application is a Spring Boot REST service on port 8080 by default, using MySQL via JPA/Hibernate. The repository does not configure production monitoring, tracing, metrics, alerting, backups, or a deployment provider.

## Routine activity

| Frequency | Activity | Evidence |
|---|---|---|
| Every change | Run Maven tests and review diff | CI run and commit |
| Every release | Verify API, auth, DB, sanitized logs | Release checklist |
| Regularly | Review dependency/security advisories | Dependency review |
| Regularly | Verify database backups/restores | Recovery evidence |
| Config change | Validate secret rotation | Change record |
| Incident | Record impact and timeline | Incident report |

## Incident severity

- **P1 Critical:** credential compromise, unauthorized cross-account access, broad outage.
- **P2 High:** dispatch corruption, widespread API failure, payment-record integrity failure.
- **P3 Moderate:** isolated endpoint failure or limited inconsistency.
- **P4 Low:** documentation defect or minor operational inconvenience.

## First response

1. Establish impact and affected data classes.
2. Preserve logs securely, without copying secrets into public tickets.
3. Restrict affected access paths.
4. Rotate compromised credentials and signing keys.
5. Disable risky booking/payment behavior if integrity is uncertain.
6. Restore from a known-good release/database backup when appropriate.
7. Record timeline, impact, root cause, and corrective actions.

## Sensitive data

Never log or publish passwords, hashes, JWTs, database credentials, driver licence data, passenger contact details, precise pickup/drop-off addresses, or payment credentials.

The current driver login service contains credential logging. Remove it before any deployment and assess historical logs.

## Backup and recovery

No backup job is configured in this repository. A real environment needs backup frequency, encryption, retention, RPO/RTO, restore tests, and named recovery ownership.

Do not claim monitoring, uptime objectives, backup compliance, or disaster recovery until those systems exist and are tested.
