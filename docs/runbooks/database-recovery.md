# 🗄️ Database Recovery Planning Runbook

## Current repository capability

No production backup job, migration framework, or database restore automation is configured. This is a planning checklist, not an executable recovery procedure.

## Before a production environment exists
- Select a database provider and backup mechanism.
- Define backup frequency, encryption, access, and retention.
- Define RPO and RTO.
- Document migration version and schema compatibility.
- Define restore test cadence and approvers.
- Store recovery credentials securely.

## During recovery
1. Declare incident and freeze unsafe writes.
2. Preserve forensic evidence securely.
3. Select the approved recovery point.
4. Restore to an isolated environment first.
5. Verify schema, referential integrity, and record counts.
6. Confirm application compatibility.
7. Obtain approval before switching traffic.
8. Record data loss window and follow-up actions.

Never paste database dumps or sensitive passenger/driver records into public tickets.
