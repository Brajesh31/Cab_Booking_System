# 🔄 Ride Lifecycle

The `RideStatus` enum contains:

~~~java
REQUESTED
ASSIGNED
ONGOING
COMPLETED
CANCELLED
~~~

Conceptual lifecycle:

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

The current service does not enforce this graph. It accepts a string and calls `RideStatus.valueOf(status)`. A future implementation should define allowed transitions in a domain policy and test each transition and actor permission.
