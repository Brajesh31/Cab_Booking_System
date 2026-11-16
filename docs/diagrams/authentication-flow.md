# 🔐 JWT Authentication Flow

~~~mermaid
sequenceDiagram
    actor Client
    participant Controller as Login Controller
    participant Service as Account Service
    participant Repo as Account Repository
    participant JWT as JwtUtil
    participant Filter as JwtAuthenticationFilter
    participant Target as Protected Controller
    Client->>Controller: Submit credentials
    Controller->>Service: Authenticate
    Service->>Repo: Find user or driver
    Repo-->>Service: Account record
    Service->>JWT: Generate signed token
    JWT-->>Client: JWT string
    Note over Service: Driver login currently logs password and hash
    Client->>Filter: Request with Bearer token
    Filter->>JWT: Parse and validate token
    Filter->>Repo: Reload principal details
    Filter->>Target: Continue authenticated request
~~~

A valid token does not prove that its holder owns a resource identified by a URL parameter. Ownership must be checked separately in service logic.
