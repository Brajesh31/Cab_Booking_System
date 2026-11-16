# 🗂️ Project Structure and File Responsibilities

The following tree explains the Java source organization and documentation layout. Existing source files remain authoritative.

~~~text
Cab_Booking_System/
├── .github/                  # CI, issue forms, PR template
├── .mvn/wrapper/             # Maven Wrapper configuration
├── docs/
│   ├── api/                  # API contract and request examples
│   ├── architecture/         # Editable Mermaid diagrams
│   ├── database/             # Data model and lifecycle
│   ├── decisions/            # Architecture decision records
│   ├── runbooks/             # Operational procedures
│   ├── templates/            # Review/report templates
│   ├── assets/               # Sanitized documentation visuals
│   └── ...                   # Guides and policies
├── src/main/java/com/cabbookingsystem/
│   ├── config/               # Security and OpenAPI configuration
│   ├── controller/           # REST endpoint mappings
│   ├── dto/                  # Request payload types
│   ├── entity/               # JPA persistence models
│   ├── enums/                # Domain constants
│   ├── exception/            # Exceptions and HTTP error mapping
│   ├── repository/           # Spring Data queries
│   ├── service/              # Service interfaces/implementations
│   └── util/                 # JWT and user details helpers
├── src/main/resources/       # Application configuration
├── src/test/java/            # Existing Spring context test
├── .env.example
├── mvnw / mvnw.cmd
├── pom.xml
└── ...
~~~

## Package responsibilities

| Package | Responsibility | Keep out |
|---|---|---|
| config | Framework/infrastructure configuration | Domain workflows |
| controller | HTTP mapping and method security | SQL and complex business logic |
| dto | Request/response contracts | Persistence behavior |
| entity | JPA state and relationships | Direct public API serialization |
| enums | Domain constants | Runtime configuration |
| exception | Domain errors and transport mapping | Secret-bearing debug output |
| repository | Database access abstractions | HTTP concerns |
| service | Application operations | Request parsing |
| service/impl | Concrete business workflows | Response serialization |
| util | JWT and security helpers | General business rules |

## Request execution

~~~mermaid
flowchart TD
  A[HTTP request] --> B[Security filter chain]
  B --> C[JWT filter for protected route]
  C --> D[Controller]
  D --> E[Request DTO]
  E --> F[Service]
  F --> G[Repository]
  G --> H[(MySQL)]
  H --> G
  G --> F
  F --> D
  D --> I[HTTP response]
~~~

## Maintenance rules

- Put request and response contracts in dto/.
- Keep persistence mapping in entity/.
- Keep queries in repository/.
- Keep business rules in service/.
- Keep HTTP mapping and status codes in controller/.
- Keep secrets out of source and logs.
- Add tests under src/test/java/ using matching package names.
- Update API, DB, security, and diagrams when behavior changes.

## Security requirement

Controllers should not serialize User, Driver, Ride, or Payment entities directly. Use allow-listed response DTOs. Enforce ownership in the service layer using the authenticated principal, never a caller-supplied ID alone.
