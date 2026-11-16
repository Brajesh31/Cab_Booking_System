# ▶️ Local Startup Runbook

1. Install JDK 21 and confirm `java -version`.
2. Install/start MySQL.
3. Create a disposable database named `cab_booking_db`.
4. Set datasource credentials and a unique JWT secret as environment variables.
5. Confirm port 8080 is available.
6. Run `./mvnw test`.
7. Run `./mvnw spring-boot:run`.
8. Inspect startup output for database errors and secret-bearing logs.
9. Use a local API client with synthetic records only.
10. Stop the process with Ctrl+C.

Do not use real people, phone numbers, credentials, licence identifiers, precise addresses, or payment details in local test data.
