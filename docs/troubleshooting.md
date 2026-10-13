# Troubleshooting

## Application cannot connect to MySQL

Confirm the local MySQL service is running and the database named in **SPRING_DATASOURCE_URL** exists. Check the username/password variables and JDBC URL. The application properties include local defaults; override them with environment variables. Never paste connection values into a public issue.

## JWT startup fails

The JWT utility creates an HMAC key from **jwt.secret**. Supply a strong random secret of sufficient length through **JWT_SECRET**. Do not use the checked-in signing default or share a secret between environments. Changing the signing key invalidates existing tokens.

## A request returns 401

Confirm login returned a JWT and send it as **Authorization: Bearer <token>**. Check token expiration and the server's configured JWT secret. User tokens use email as subject; driver tokens use phone.

## A request returns 403

The route may require another role. The token's role claim is loaded into Spring authorities as **ROLE_USER**, **ROLE_DRIVER**, or **ROLE_ADMIN**. Role access does not establish that a user owns the record ID in the request.

## SQL schema changes appear automatically

The default **spring.jpa.hibernate.ddl-auto** value is **update**. Hibernate may change the local schema at startup. This project has no migration history or rollback process; avoid using an important database until migrations are introduced.

## API documentation is missing

The Maven project includes Springdoc WebMVC UI. The security configuration permits **/swagger-ui/** and **/v3/api-docs/**. The source OpenAPI configuration class is commented out, so descriptions and bearer-auth metadata may be incomplete.

## Logs contain passwords or hashes

Driver login currently logs the submitted password and stored hash. Treat any captured log as sensitive, restrict access, and remove these log statements before use with real accounts. Never attach the output to an issue.

## A profile or driver response contains private fields

Several routes serialize JPA entities directly. This can return password hashes, license fields, or nested personal data. The route should be changed to return a purpose-built response DTO before real records are used.

## Where should I report a vulnerability?

Follow the private reporting instructions in [Security](../SECURITY.md). For ordinary setup questions, see [Support](../SUPPORT.md).
