# 📜 Changelog

This file records notable repository changes. The original application snapshot has no reliable release date recorded here.

## [Unreleased]

### ✨ Added
- Central documentation hub and expanded project overview, feature inventory, project structure, testing, release, and operations guides.
- Mermaid architecture, authentication, ride-booking, lifecycle, and contribution diagrams.
- API request examples, a Postman collection, and a draft OpenAPI contract.
- Database entity and lifecycle references.
- Reusable templates for feature design, bug reports, security reviews, test plans, incidents, releases, API changes, and migrations.
- Architecture decision records and operational runbooks.
- GitHub Actions Maven CI, CodeQL analysis, Dependabot configuration, and issue/PR templates.

### 🛡️ Security documentation
- Documented source-observed credential logging, fixed JWT secret defaults, weak local database defaults, entity serialization, and missing ownership checks.
- Clarified that payment records marked SUCCESS are not payment-provider transactions and that ride fares are random demo values.

### 📝 Notes
- Documentation additions do not remediate application vulnerabilities.
- CI and analysis workflows were added; results must be checked for the commit being released.
- No production deployment, security certification, or full integration test coverage is claimed.
