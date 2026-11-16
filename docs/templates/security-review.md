# 🔐 Security Review

## Change under review
- Pull request:
- Commit:
- Reviewer:
- Date:

## Assets and trust boundaries
Identify credentials, identity fields, ride locations, driver records, payments, and external systems.

## Threat scenarios
| Scenario | Preconditions | Impact | Existing control | Gap |
|---|---|---|---|---|
| Cross-user resource access | | | | |
| Credential exposure | | | | |
| Token misuse | | | | |
| Sensitive response serialization | | | | |
| Payment spoofing/replay | | | | |
| Driver dispatch race | | | | |

## Authentication and authorization
- [ ] Authentication required on protected routes.
- [ ] Role checks are correct.
- [ ] Resource ownership enforced server-side.
- [ ] Admin overrides explicit and audited.

## Data protection
- [ ] No secrets committed.
- [ ] No sensitive data logged.
- [ ] Response DTOs exclude secrets.
- [ ] Validation and retention reviewed.

## Verification evidence
List automated tests, negative tests, scans, and manual checks. Distinguish not-run from passed.

## Findings and remediation
| Finding | Severity | Owner | Target | Status |
|---|---|---|---|---|

## Decision
- [ ] Approve
- [ ] Approve with tracked follow-up
- [ ] Request changes
