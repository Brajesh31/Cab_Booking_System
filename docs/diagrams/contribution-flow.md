# 🤝 Contribution Flow

~~~mermaid
flowchart TD
    A[Choose issue] --> B[Understand existing source]
    B --> C[Define acceptance criteria]
    C --> D[Check security and data impact]
    D --> E[Implement focused change]
    E --> F[Add unit and integration tests]
    F --> G[Run Maven checks]
    G --> H[Update API, DB, and architecture docs]
    H --> I[Open pull request]
    I --> J[Review]
    J --> K{Approved?}
    K -->|No| E
    K -->|Yes| L[Merge]
~~~
