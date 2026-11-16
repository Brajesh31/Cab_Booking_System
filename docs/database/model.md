# 🧬 Current Entity Model

## Entity inventory

| Entity | Important fields observed | Relationships |
|---|---|---|
| User | userId, name, email, phone, passwordHash, role, createdAt | One rider can have many rides |
| Driver | driverId, name, phone, licenseNumber, vehicleDetails, passwordHash, available, role | One driver can have many rides |
| Ride | rideId, pickupLocation, dropoffLocation, fare, status | Many-to-one User; many-to-one Driver |
| Payment | paymentId, amount, method, status, timestamp | One-to-one Ride; many-to-one User |
| Rating | ratingId, fromUserId, toUserId, score, comments | References ride and user identities by fields |

The source does not define an explicit schema migration history. Verify naming strategy and actual generated MySQL DDL before making operational assumptions.

## Relationship diagram

~~~mermaid
erDiagram
    USER ||--o{ RIDE : requests
    DRIVER ||--o{ RIDE : assigned
    RIDE ||--o| PAYMENT : has
    USER ||--o{ PAYMENT : records
    RIDE ||--o{ RATING : receives
~~~

The diagram captures declared JPA relationships conceptually. Rating's user references are identifier fields in the current entity, not necessarily enforced JPA relationships.

## Data classification

| Data | Sensitivity | Handling |
|---|---|---|
| Password hash | Highly sensitive | Never return or log |
| JWT signing secret | Critical secret | External secret store only |
| Driver licence number | Sensitive identity data | Minimize and restrict |
| Phone/email | Personal data | Access controlled |
| Pickup/drop-off text | Sensitive location data | Minimize retention and logging |
| Payment amount/status | Financial metadata | Verify source and access |
| Rating/comment | User-generated data | Validate and moderate |

## Improvement direction

Use explicit foreign-key constraints and indexes, versioned migrations, response DTOs, privacy-aware retention, and constraints that reflect the intended ride/payment lifecycle.
