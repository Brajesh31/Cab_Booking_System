# 🗄️ Database Migration Checklist

## Migration identity
- Migration ID:
- Author:
- Related issue:
- Target release:

## Change impact
- Tables and columns:
- Constraints and indexes:
- Data backfill:
- Estimated lock/downtime impact:
- PII/location impact:

## Safety
- [ ] Migration is versioned and deterministic.
- [ ] Forward migration tested on a disposable database.
- [ ] Existing data compatibility checked.
- [ ] Unique/null/foreign-key constraints reviewed.
- [ ] Rollback or forward-fix approach documented.
- [ ] Backup verified before production execution.
- [ ] Application compatibility during rollout assessed.
- [ ] No credentials or personal data in migration fixtures.

## Verification
- [ ] Schema state checked.
- [ ] Repository queries tested.
- [ ] Data integrity checks passed.
- [ ] Application startup and smoke tests passed.
