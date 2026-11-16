# 🏛️ System Architecture

~~~mermaid
flowchart TB
    Client[REST Client] --> Security[Spring Security Filter Chain]
    Security --> JWT[JWT Filter]
    JWT --> Controller[Spring MVC Controllers]
    Controller --> DTO[Request DTOs]
    DTO --> Service[Service Interfaces]
    Service --> Impl[Service Implementations]
    Impl --> Repo[Spring Data JPA Repositories]
    Repo --> Hibernate[Hibernate ORM]
    Hibernate --> DB[(MySQL)]
    Repo --> Impl
    Impl --> Controller
    Controller --> Client
~~~

## Boundary notes
- Controllers handle HTTP mapping and method-level role annotations.
- Services implement current use cases.
- Repositories access persisted data.
- JPA entities are persistence models, not safe response contracts.
- MySQL stores users, drivers, rides, payment records, and ratings.

## Current risk
Entity serialization and incomplete resource ownership checks weaken the API boundary. Replace direct entity responses with explicit DTOs and enforce principal-scoped repository access before external use.
