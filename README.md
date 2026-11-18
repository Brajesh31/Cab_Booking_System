# 🚕 Cab Booking System API

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 3.5">
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/Maven-Wrapper-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/JWT-Bearer%20Auth-black?style=for-the-badge" alt="JWT">
  <img src="https://img.shields.io/badge/License-MIT-2E8B57?style=for-the-badge" alt="MIT">
</p>

<p align="center"><strong>A Spring Boot REST API prototype for rider and driver accounts, ride booking, ride status, payment records, and ratings.</strong></p>

---

## 📌 Project overview

**Cab Booking System** is a backend REST API built with Java 21 and Spring Boot 3.5. It exposes endpoints for rider registration/login, driver registration/login, driver availability, ride booking and status updates, payment-record creation, and ratings.

The application uses Spring MVC for HTTP endpoints, Spring Security with JWT bearer tokens for authentication, Spring Data JPA/Hibernate for persistence, and MySQL as its relational database.

> 🚨 **Important: this repository is a prototype, not a production-ready ride-hailing or payment platform.** The inspected source contains serious security and domain limitations: driver login logs the submitted password and stored password hash; application properties contain weak local database credentials and a fixed JWT signing secret; some API responses serialize entities with sensitive fields; and several ID-based operations do not enforce record ownership. Payment processing only saves a record marked SUCCESS, and ride fare is randomly generated. Do not deploy this version with real customer, driver, location, licence, or payment data.

Read [Security policy](SECURITY.md) and [Security review](docs/SECURITY_REVIEW.md) before using the API outside a disposable local environment.

## 🎯 Current capabilities

| Domain | Current behavior |
|---|---|
| 👤 Rider accounts | Registration, login, profile lookup |
| 🚘 Driver accounts | Registration, login, availability lookup/update |
| 🛣️ Rides | Book ride, update status, list rider rides |
| 💳 Payment records | Persist a payment record and retrieve a receipt |
| ⭐ Ratings | Submit ratings and query by target user |
| 🔐 Authentication | Stateless JWT bearer authentication |
| 🧯 Error handling | Selected exception-to-status mappings |

### ⚠️ Prototype limitations
- **Credential exposure:** Driver login prints the submitted password and stored hash.
- **Unsafe defaults:** `application.properties` contains `root` / `root` database credentials and a fixed JWT signing secret.
- **Sensitive API serialization:** Some controllers return JPA entities directly. User/driver entities contain credential-related and identity fields.
- **Incomplete ownership checks:** Several ID-based operations do not verify that the authenticated principal owns the resource.
- **Simulated payment:** The service stores a record with SUCCESS status. It does not integrate with a payment gateway or verify settlement.
- **Random fare:** Ride creation generates a random fare rather than using a documented pricing model.
- **Simplistic dispatch:** Booking selects the first available driver. There is no distance matching, locking, or concurrency-safe dispatch strategy.
- **Migration gap:** Hibernate schema auto-update is enabled. There are no versioned database migration scripts.
- **Limited tests:** The current test source contains only a Spring application context-load test.

These are source-confirmed limitations, not merely future risks.

## 🧰 Technology stack

| Technology | Role |
|---|---|
| Java 21 | Application language/runtime |
| Spring Boot 3.5.0 | Application framework |
| Spring Web | REST controllers and HTTP |
| Spring Security | Authentication/authorization |
| JJWT 0.11.5 | JWT creation and parsing |
| Spring Data JPA / Hibernate | ORM and persistence |
| MySQL Connector/J | MySQL connectivity |
| Hibernate Validator | Validation dependency |
| Springdoc OpenAPI UI 2.5.0 | API documentation dependency |
| Lombok | Boilerplate reduction |
| Maven Wrapper | Build commands |
| JUnit / Spring Boot Test | Test dependencies |

Exact dependencies and versions are declared in [pom.xml](pom.xml).

## 🏗️ Architecture

~~~mermaid
flowchart TD
    A[REST Client] --> B[Spring Security Filter Chain]
    B --> C{Registration or login?}
    C -->|Public| D[Controller]
    C -->|Protected| E[JWT Authentication Filter]
    E --> F{Valid token?}
    F -->|No| G[401 Unauthorized]
    F -->|Yes| H[Controller role checks]
    D --> I[Service Layer]
    H --> I
    I --> J[Spring Data JPA Repository]
    J --> K[(MySQL)]
    K --> J
    J --> I
    I --> L[HTTP Response]
~~~

See [Architecture](docs/architecture.md) and [architecture diagrams](docs/diagrams/README.md).

## 🗂️ Repository structure

~~~text
Cab_Booking_System/
├── .github/                    # CI, issue forms, PR template, automation
├── .mvn/wrapper/               # Maven Wrapper configuration
├── docs/
│   ├── api/                    # API contract and request examples
│   ├── architecture/           # Editable Mermaid diagrams
│   ├── database/               # Entity model and lifecycle notes
│   ├── decisions/              # Architecture Decision Records
│   ├── runbooks/               # Operational procedures
│   ├── templates/              # Reusable review/report templates
│   └── assets/                 # Documentation visuals
├── src/main/java/com/cabbookingsystem/
│   ├── config/                 # Security and OpenAPI configuration
│   ├── controller/             # REST endpoints
│   ├── dto/                    # Request payload objects
│   ├── entity/                 # JPA entities
│   ├── enums/                  # Domain enums
│   ├── exception/              # Exceptions and error mapping
│   ├── repository/             # Spring Data repositories
│   ├── service/                # Service interfaces and implementations
│   └── util/                   # JWT and user-details components
├── src/main/resources/         # Spring configuration
├── src/test/java/              # Existing application context test
├── .env.example
├── mvnw / mvnw.cmd
├── pom.xml
└── ...
~~~

See [Project Structure](docs/PROJECT_STRUCTURE.md) for detailed file responsibilities.

## ⚙️ Prerequisites
- JDK 21.
- MySQL server and a local disposable database.
- Git and a terminal or IDE.
- Maven Wrapper included in this repository.

~~~bash
java -version
./mvnw -version
~~~

On Windows PowerShell, use `.\mvnw.cmd -version`.

## 🚀 Local setup

### 1. Clone

~~~bash
git clone https://github.com/Brajesh31/Cab_Booking_System.git
cd Cab_Booking_System
~~~

### 2. Create a disposable database

~~~sql
CREATE DATABASE cab_booking_db;
~~~

Use a dedicated local database account. Do not use real customer or driver records.

### 3. Configure environment

`.env.example` documents variable names, but Spring Boot does not load that file automatically. Export variables in your shell or configure them in your IDE.

**macOS / Linux**

~~~bash
export SERVER_PORT=8080
export SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3306/cab_booking_db?serverTimezone=UTC'
export SPRING_DATASOURCE_USERNAME='your_local_db_user'
export SPRING_DATASOURCE_PASSWORD='your_local_db_password'
export JWT_SECRET='replace-with-a-unique-random-secret-at-least-32-bytes'
export JWT_EXPIRATIONMS='86400000'
./mvnw spring-boot:run
~~~

**Windows PowerShell**

~~~powershell
$env:SERVER_PORT = "8080"
$env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/cab_booking_db?serverTimezone=UTC"
$env:SPRING_DATASOURCE_USERNAME = "your_local_db_user"
$env:SPRING_DATASOURCE_PASSWORD = "your_local_db_password"
$env:JWT_SECRET = "replace-with-a-unique-random-secret-at-least-32-bytes"
$env:JWT_EXPIRATIONMS = "86400000"
.\mvnw.cmd spring-boot:run
~~~

Generate a unique cryptographically random JWT signing key. Never use the committed default. Do not commit secrets.

### 4. Start

macOS / Linux:

~~~bash
./mvnw spring-boot:run
~~~

Windows:

~~~powershell
.\mvnw.cmd spring-boot:run
~~~

The API listens on `http://localhost:8080` by default. Routes are under `/api/`.

> ⚠️ Hibernate is configured with `ddl-auto=update`. Use a disposable local database until a reviewed migration strategy replaces automatic schema updates.

## 🧪 Maven commands

| Command | Purpose |
|---|---|
| `./mvnw clean` | Remove generated Maven output |
| `./mvnw compile` | Compile source |
| `./mvnw test` | Run configured tests |
| `./mvnw package` | Build application JAR |
| `./mvnw spring-boot:run` | Launch locally |
| `./mvnw dependency:tree` | Inspect dependency resolution |

Windows users should substitute `.\mvnw.cmd`.

Documentation changes do not mean these commands have been executed. Existing tests do not prove endpoint authorization, payment integrity, or domain correctness.

## 🔑 Authentication

1. Register a rider or driver.
2. Log in through the corresponding endpoint.
3. Receive a JWT string.
4. Send it on protected routes:

~~~http
Authorization: Bearer <your-jwt>
~~~

Rider tokens use email as subject. Driver tokens use phone number as subject. The JWT role claim is converted into Spring Security authorities by the authentication utility layer.

A valid token does not prove that its holder owns a resource identified by a URL parameter. Record ownership must be checked separately.

## 📡 API quick reference

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/users/register` | Register rider |
| POST | `/api/users/login` | Rider login |
| GET | `/api/users/profile/{userId}` | Read profile by ID |
| POST | `/api/drivers/register` | Register driver |
| POST | `/api/drivers/login` | Driver login |
| GET | `/api/drivers/available` | List available drivers |
| PUT | `/api/drivers/status/{id}?available=true` | Change driver availability |
| POST | `/api/rides/book` | Create a ride |
| PUT | `/api/rides/status/{id}?status=ONGOING` | Update ride state |
| GET | `/api/rides/user/rides` | List current rider rides |
| POST | `/api/payments/process` | Store a payment record |
| GET | `/api/payments/receipt/{rideId}` | Read payment by ride ID |
| POST | `/api/ratings` | Submit ride rating |
| GET | `/api/ratings/user/{toUserId}` | Query target user's ratings |

See the detailed [API reference](docs/api/README.md) for payloads, examples, and security caveats.

## 🔄 Ride lifecycle

~~~mermaid
stateDiagram-v2
    [*] --> REQUESTED
    REQUESTED --> ASSIGNED
    ASSIGNED --> ONGOING
    ONGOING --> COMPLETED
    REQUESTED --> CANCELLED
    ASSIGNED --> CANCELLED
    ONGOING --> CANCELLED
~~~

The enum contains these status values, but the service does not enforce this transition graph. It accepts a string and calls `RideStatus.valueOf`. The diagram is conceptual, not an enforced business rule.

## 🧱 Development principles
- Keep HTTP mapping in controllers.
- Put business operations in services.
- Keep persistence access in repositories.
- Use request and response DTOs.
- Validate external input.
- Verify role and ownership separately.
- Keep secrets out of source and logs.
- Use versioned database migrations.
- Test success, denial, invalid input, concurrency, and failure paths.
- Update diagrams and docs with behavior changes.

See [CONTRIBUTING.md](CONTRIBUTING.md) and [Development](docs/development.md).

## 🛡️ Security and privacy

**Do not connect this prototype to real passenger or driver data.** The current source has known weaknesses involving credential logging, fixed secret defaults, entity serialization, and incomplete object ownership checks.

Read [Security policy](SECURITY.md), [Security review](docs/SECURITY_REVIEW.md), and [Security design guide](docs/SECURITY.md). Report vulnerabilities privately. Do not publish tokens, passwords, hashes, driver licence information, location data, or database dumps.

## 🗺️ Roadmap

The roadmap prioritizes remediation before feature growth: remove credential logging and rotate defaults; replace entity serialization with DTOs; enforce ownership; validate DTOs and ride state transitions; replace simulated payments and random fares; introduce database migrations and integration tests; make dispatch concurrency-safe; and add monitoring only after infrastructure is selected.

See [ROADMAP.md](ROADMAP.md) for priorities and exit criteria.

## 📚 Documentation

The [documentation hub](docs/README.md) covers project scope, feature inventory, architecture, API contracts, data model, development, security, testing, release process, deployment, operations, runbooks, diagrams, and reusable templates.

## 🤝 Contributing

Read [CONTRIBUTING.md](CONTRIBUTING.md), follow [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md), and use the GitHub issue/PR templates. Do not report vulnerabilities publicly.

## 📄 License

The project code and documentation are provided under the MIT License. See [LICENSE](LICENSE). Third-party dependencies retain their own licenses.

## 👤 Maintainer

Maintained by **Brajesh Kumar**.

<p align="center">Built with Java, Spring Boot, and a focus on responsible backend engineering. 🚕</p>

Last updated: 2026-11-18 01:24 UTC
