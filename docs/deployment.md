# Deployment

## Readiness

This project is a prototype and is not ready to deploy with real account, driver, ride-location, or payment data. Current blockers include a committed JWT key and local database defaults, plaintext password and password-hash logging, entity responses that expose sensitive fields, and missing object-ownership checks. The payment endpoint does not process a charge.

## Configuration before deployment

- Supply MySQL connection details and a unique JWT signing key through a secret manager or protected runtime environment.
- Never use the checked-in datasource username/password or JWT secret.
- Rotate the JWT key between environments and plan token invalidation.
- Turn off Hibernate schema auto-update, SQL output, and security DEBUG logs.
- Use HTTPS and restrict network access to the database.
- Remove sensitive logging and return explicit response DTOs.
- Enforce principal ownership for every resource read and mutation.
- Add request validation, rate limits where appropriate, and security-focused tests.
- Review privacy, data retention, deletion, backup, and incident response for user and driver data.

## Build and run

Build from the repository root with the Maven Wrapper:

    ./mvnw clean package

The executable Spring Boot artifact is produced under **target/**. Run it with **java -jar target/cabbookingsystem-0.0.1-SNAPSHOT.jar** after supplying runtime environment variables. The exact artifact name follows **pom.xml**.

## Database changes

The app currently uses **spring.jpa.hibernate.ddl-auto=update** and contains no versioned migration scripts. Do not rely on automatic update for production schema evolution. Add and review a migration process, back up the database, test upgrades and rollback, and coordinate schema and application releases.

## API documentation

The project includes Springdoc's WebMVC UI dependency and permits **/swagger-ui/** and **/v3/api-docs/** in the security configuration. The checked-in **OpenAPIConfig.java** is commented out, so verify generated descriptions and bearer-auth documentation before publishing them.
