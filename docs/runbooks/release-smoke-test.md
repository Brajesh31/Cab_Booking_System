# ✅ Release Smoke Test Runbook

Record the exact release commit and environment. Run only against a disposable or approved test environment.

- [ ] Application starts with secrets supplied externally.
- [ ] Database connection is healthy.
- [ ] User registration and login behave as expected.
- [ ] Driver registration and login behave as expected.
- [ ] Missing/invalid JWT returns 401 for protected routes.
- [ ] Wrong role returns 403.
- [ ] Cross-user profile and payment access is rejected.
- [ ] Unassigned driver cannot update a ride.
- [ ] No passwords, hashes, or tokens are written to logs.
- [ ] API responses do not include sensitive persistence fields.
- [ ] Payment records are not described as settled transactions.
- [ ] Ride status and driver availability remain consistent under concurrency tests.
- [ ] Database migration and rollback strategy is verified.

A checklist item is not passed without recorded evidence.
