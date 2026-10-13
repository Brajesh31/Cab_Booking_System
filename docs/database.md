# Database

## Engine and configuration

The application uses MySQL through Spring Data JPA and Hibernate. Connection settings are declared in **src/main/resources/application.properties** and can be overridden with Spring environment variables such as **SPRING_DATASOURCE_URL**, **SPRING_DATASOURCE_USERNAME**, and **SPRING_DATASOURCE_PASSWORD**.

The checked-in defaults target a local MySQL database named **cab_booking_db** and use a local username/password. They are development placeholders, not production credentials. **.env.example** documents variable names but Spring Boot does not read that file automatically.

## Entities

| Entity | Current fields and relationships |
| --- | --- |
| **User** | Generated ID, name, email, unique non-null phone, passwordHash, role, and createdAt string. |
| **Driver** | Generated ID, name, phone, licenseNumber, vehicleDetails, passwordHash, availability, and role. |
| **Ride** | Generated ID, many-to-one User and Driver, pickup/dropoff strings, double fare, and RideStatus enum. |
| **Payment** | Generated ID, one-to-one Ride, many-to-one User, double amount, PaymentMethod enum, status string, and timestamp string. |
| **Rating** | Generated ID, one-to-one Ride, from/to IDs, integer score, and comments. |

Exact table and column names follow JPA/Hibernate naming conventions; no explicit schema migration files are checked in.

## Schema lifecycle

The property **spring.jpa.hibernate.ddl-auto=update** instructs Hibernate to alter the database schema based on entity changes during application startup. This is configured as a local convenience. There is no Flyway or Liquibase migration history, rollback procedure, or versioned SQL migration in the repository.

Do not point this setting at production data. Introduce a reviewed migration workflow, back up before changes, and test rollback separately.

## Sensitive fields and data integrity

- Password hashes are stored in User and Driver entities, but current controller responses return entities directly.
- Ride locations and driver license details are sensitive personal data.
- **double** is used for ride fares and payment amounts; decimal money semantics and currency are not defined.
- Payment status is stored as a string and is set to **SUCCESS** without a payment processor.
- **createdAt** and payment **timestamp** are strings rather than temporal database types.
- Relationships and ownership constraints do not enforce all authorization rules at the database layer.

Use synthetic local records only until response DTOs, ownership enforcement, data retention, backup, and privacy policies are established.
