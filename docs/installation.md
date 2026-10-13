# Installation

## Requirements

- JDK 21.
- MySQL server.
- The checked-in Maven Wrapper; a separate Maven installation is not required.
- A terminal and HTTP client such as curl or an API client.

Do not use real rider, driver, location, license, or payment data. The current API has known credential logging, response-data exposure, and record ownership gaps; see [Security](../SECURITY.md).

## Create a local database

Create a disposable local MySQL database before starting the application. For example:

    CREATE DATABASE cab_booking_db;

Use a dedicated local database account rather than a shared or production account. The application properties currently contain local defaults; override them with environment variables.

## Configure environment

**.env.example** lists the Spring environment variable names but Spring Boot does not load that file automatically. Export the values in your shell or configure them in your IDE.

Windows PowerShell:

    $env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/cab_booking_db?useSSL=false&serverTimezone=UTC"
    $env:SPRING_DATASOURCE_USERNAME = "your_local_db_user"
    $env:SPRING_DATASOURCE_PASSWORD = "your_local_db_password"
    $env:JWT_SECRET = "replace-with-a-unique-random-secret-at-least-32-bytes"
    $env:JWT_EXPIRATION_MS = "86400000"

macOS or Linux:

    export SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3306/cab_booking_db?useSSL=false&serverTimezone=UTC'
    export SPRING_DATASOURCE_USERNAME='your_local_db_user'
    export SPRING_DATASOURCE_PASSWORD='your_local_db_password'
    export JWT_SECRET='replace-with-a-unique-random-secret-at-least-32-bytes'
    export JWT_EXPIRATION_MS='86400000'

The JWT key must be sufficiently long for the configured HMAC algorithm. Generate a unique random value; never use the checked-in default in a public deployment.

## Start the application

From the repository root, run:

Windows:

    .\mvnw.cmd spring-boot:run

macOS or Linux:

    ./mvnw spring-boot:run

The API listens on **http://localhost:8080** by default. The application uses JPA schema auto-update, so review [Database](database.md) before pointing it at any database with data.

## Build and test commands

The Maven Wrapper declares the following standard project commands:

- **./mvnw test**
- **./mvnw package**

On Windows, use **mvnw.cmd**. The repository contains one context-load test. These commands were not run during this documentation update.
