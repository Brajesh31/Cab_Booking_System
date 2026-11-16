# 🚀 Release and Change Management

A backend release includes reviewed code, a compatible schema change, a tested API contract, and a reversible deployment. Compilation alone is insufficient.

## Release flow

~~~mermaid
flowchart TD
    A[Focused change] --> B[Review API and data impact]
    B --> C[Compile and test]
    C --> D[Security and integration tests]
    D --> E[Review migrations and configuration]
    E --> F{Release gates pass?}
    F -- No --> G[Fix and repeat]
    G --> C
    F -- Yes --> H[Build immutable artifact]
    H --> I[Deploy to staging]
    I --> J[Smoke and ownership tests]
    J --> K[Approve release]
    K --> L[Monitor and record]
~~~

## Pre-release checklist

- [ ] API compatibility and error behavior reviewed.
- [ ] DTO validation and response allow-lists tested.
- [ ] Role and ownership tests pass.
- [ ] Secrets externalized and rotated where required.
- [ ] Sensitive values absent from logs.
- [ ] Fare and payment behavior accurately represented.
- [ ] Dispatch concurrency tested where relevant.
- [ ] Database migrations reviewed and recovery strategy documented.
- [ ] `./mvnw clean test` succeeds.
- [ ] Integration tests run against an isolated database.
- [ ] Dependency and static security scans reviewed.
- [ ] OpenAPI and docs updated.
- [ ] Rollback owner and procedure confirmed.
- [ ] Artifact version/checksum recorded.

## Staging smoke tests

Confirm startup with non-default secrets, DB connectivity, public route behavior, 401/403 behavior, cross-user access denial, absence of sensitive response fields, payment correctness, and sanitized logs.

## Release notes

Update root CHANGELOG.md with actual changes, migration requirements, and breaking API changes. Do not report a feature as shipped merely because its docs were added.
