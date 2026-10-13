# Contributing

Thank you for considering a contribution to Cab Booking System. Changes should improve correctness, security, maintainability, accessibility, or API clarity.

## Before you begin

- Read the [Code of Conduct](CODE_OF_CONDUCT.md), [Security Policy](SECURITY.md), and [License](LICENSE).
- Review the [README](README.md), [Roadmap](ROADMAP.md), and relevant technical docs.
- Open an issue before a substantial API, schema, or authentication change.
- Do not use real rider, driver, license, location, or payment data in development.

## Development setup

Use JDK 21, MySQL, and the Maven Wrapper. Configure a disposable local database and a strong local JWT secret using environment variables described by **.env.example**. From the repository root, use **./mvnw spring-boot:run** or **mvnw.cmd spring-boot:run** on Windows.

The project currently has insecure committed defaults, logs sensitive authentication data, returns entities containing hash fields, and lacks consistent object ownership checks. Do not expose the app or use real records until these issues have been fixed.

## Code expectations

- Keep controllers focused on HTTP mapping and validate request DTOs at the boundary.
- Authenticate and authorize each resource operation, including ownership of the specific ride, driver, payment, rating, and profile.
- Return response DTOs rather than JPA entities or password hashes.
- Never log passwords, token values, hashes, or sensitive ride details.
- Use server-generated values for fare and payment status; do not treat a stored **SUCCESS** value as payment processing.
- Keep JWT keys and database credentials outside source control.
- Use schema migrations for deliberate database changes instead of relying on automatic schema update in production.
- Avoid exposing SQL and security debug logs in production.
- Document API changes, status transitions, error behavior, and migrations.

## Pull requests

Include the user problem, relevant routes and roles, request/response changes, validation and ownership checks, database migration and rollback notes, and tests or manual checks actually performed. Include example payloads without personal data and screenshots only when they help review UI/API documentation. Keep each pull request focused and do not claim production readiness without evidence.
