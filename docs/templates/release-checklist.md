# 🚀 Release Checklist

## Build and source
- [ ] Reviewed commit and dependency changes.
- [ ] `./mvnw clean test` passes.
- [ ] `./mvnw package` passes.
- [ ] Artifact checksum and version recorded.

## Security
- [ ] No credentials/signing secrets in source.
- [ ] No password/hash/token logging.
- [ ] DTOs exclude sensitive fields.
- [ ] Role and ownership tests pass.
- [ ] Security findings closed or explicitly block release.

## Data
- [ ] Migration reviewed and tested.
- [ ] Backup and recovery plan verified.
- [ ] Rollback compatibility assessed.
- [ ] Retention/privacy requirements reviewed.

## API and behavior
- [ ] OpenAPI contract updated.
- [ ] Request validation confirmed.
- [ ] Payment/fare status accurate.
- [ ] Error semantics reviewed.
- [ ] Idempotency and concurrency tested where required.

## Operations
- [ ] Runtime secrets configured.
- [ ] Network and TLS controls reviewed.
- [ ] Health and alerting verified.
- [ ] Logs sanitized.
- [ ] Staging smoke tests complete.
- [ ] Rollback owner/procedure confirmed.

## Approval
- Release owner:
- Commit:
- Date:
- Decision:
