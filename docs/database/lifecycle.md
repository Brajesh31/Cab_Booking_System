# 🔄 Data Lifecycle and Retention

## Current observed persistence

The application persists user/driver records, rides, payment records, and ratings. Hibernate is configured for automatic schema update. No scheduled deletion, anonymization, archive, backup, or retention mechanism is present in the repository.

## Sensitive data categories

- Account identifiers and contact details.
- Password hashes.
- Driver licence and vehicle information.
- Pickup and drop-off location text.
- Ride and payment metadata.
- User rating comments.

## Required future policy

Before real data is collected, define:
1. Purpose and lawful basis for every personal data field.
2. Minimum required data at collection.
3. Who may read/update each category.
4. Retention period and deletion/anonymization trigger.
5. Backup retention and deletion propagation.
6. Audit events for sensitive access.
7. User data access and correction procedures.
8. Incident response and breach assessment.

Do not invent retention periods in code or documentation without product, legal, and operational approval.
