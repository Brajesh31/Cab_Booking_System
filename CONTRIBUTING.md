# 🤝 Contributing Guide

Thank you for considering a contribution to Cab Booking System. Changes should improve correctness, security, maintainability, or API clarity.

## 🧭 Before you begin
- Read the [Code of Conduct](CODE_OF_CONDUCT.md), [Security Policy](SECURITY.md), and [License](LICENSE).
- Review the [README](README.md), [Roadmap](ROADMAP.md), and relevant technical docs.
- Open an issue before substantial API, schema, authentication, or domain changes.
- Do not use real rider, driver, licence, location, or payment data in development.
- Security fixes take priority over feature expansion.

## ⚙️ Development setup
Use JDK 21, MySQL, and the Maven Wrapper. Configure a disposable local database and a unique local JWT secret using environment variable names in `.env.example`. Spring Boot does not automatically load the `.env.example` file.

macOS/Linux: `./mvnw spring-boot:run`. Windows: `mvnw.cmd spring-boot:run`.

Run baseline checks:
~~~bash
./mvnw clean test
./mvnw package
~~~

## 🚨 Existing risks contributors must not ignore
- The driver login service logs a submitted password and a stored password hash.
- `application.properties` contains weak local database credentials and a fixed JWT signing secret.
- Some endpoints serialize JPA entities that contain sensitive fields.
- Several ID-based operations do not consistently enforce record ownership.
- The payment service stores a record marked SUCCESS without calling a payment provider.
- Ride fare is generated randomly and driver dispatch chooses the first available driver.

Do not expose this application or use real data until these problems are remediated.

## 🧱 Code expectations
- Keep controllers focused on HTTP mapping.
- Put domain decisions in services and persistence queries in repositories.
- Use validated request DTOs and explicit response DTOs.
- Authenticate and authorize each resource operation, including record ownership.
- Never log passwords, token values, hashes, or sensitive ride details.
- Use server-generated values for fare and payment state.
- Keep JWT keys and database credentials outside source control.
- Use schema migrations for deliberate database changes.
- Avoid SQL and security DEBUG logs in production.
- Document API changes, state transitions, errors, and migrations.

## 🧪 Testing expectations
Add tests for successful flows, invalid input, denied roles, cross-account access, data serialization, concurrency, and failure paths. A context-load test alone is not sufficient for business/security changes.

## 📬 Pull request requirements
Include the problem, implementation summary, affected routes and roles, request/response changes, validation and ownership checks, database migration/rollback notes, and tests actually executed. Use only synthetic example data. Keep the pull request focused.

## 📚 Documentation updates
Update API, architecture, database, security, testing, and runbook docs when the corresponding behavior changes. Keep diagrams aligned with actual code and mark proposed behavior as proposed.
