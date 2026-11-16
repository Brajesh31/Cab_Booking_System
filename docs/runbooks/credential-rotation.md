# 🔑 Credential Rotation Runbook

## Trigger conditions
Rotate when a secret is committed, logged, shared, suspected compromised, or required by policy.

## Current project risks
The repository has a committed JWT secret default, weak local database credential defaults, and driver login logging of passwords and hashes. Treat these as blockers before any real deployment.

## Rotation sequence
1. Identify affected environments and consumers.
2. Generate a unique cryptographically random replacement secret.
3. Store it in the approved secret manager or deployment environment.
4. Deploy code that requires external configuration and rejects weak defaults.
5. Rotate the database account password using least privilege.
6. Rotate JWT signing key and invalidate tokens signed by the old key.
7. Remove old values from active configuration and review exposed logs.
8. Verify login and token validation with new configuration.
9. Confirm old credentials no longer work.
10. Record completion without recording secret values.

Do not include the old or new secret itself in an incident report, issue, or commit.
