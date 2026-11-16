# 🚕 Current Ride Booking Flow

~~~mermaid
flowchart TD
    A[Authenticated rider submits pickup and drop-off] --> B[RideController]
    B --> C[RideServiceImpl]
    C --> D[Resolve rider from principal]
    D --> E[Query available drivers]
    E --> F{Any driver available?}
    F -->|No| G[Throw no-driver error]
    F -->|Yes| H[Select first returned driver]
    H --> I[Mark driver unavailable]
    I --> J[Generate random demonstration fare]
    J --> K[Create ride with REQUESTED status]
    K --> L[Persist ride]
    L --> M[Return Ride entity]
~~~

## Current limitations
- No distance or location matching.
- No concurrency-safe driver reservation strategy.
- Random fare is not a pricing calculation.
- Response returns a JPA entity.
- Driver assignment and ride state need stronger invariants and tests.

This diagram describes inspected code and is not a production recommendation.
