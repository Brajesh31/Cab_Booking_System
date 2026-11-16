# 🗄️ Database Documentation

The application uses MySQL via Spring Data JPA and Hibernate.

- [Entity model](model.md)
- [Data lifecycle and retention](lifecycle.md)

## Current limitations
- Hibernate `ddl-auto=update` is configured.
- No Flyway or Liquibase migrations are present.
- Entities contain personal and credential-related fields.
- Direct entity serialization can disclose fields and nested relationships.
- Payment status is stored as a string, not reconciled against a provider transaction.

Treat the default setup as local development only.
