# API

## Base URL and authentication

The local base URL is **http://localhost:8080**. Registration and login routes are public. Other routes require a signed JWT in:

    Authorization: Bearer <token>

The token subject is a rider email for user login and a driver phone number for driver login. The token includes the role. The JWT filter reloads user details for each authenticated request.

## Routes

| Method | Path | Access configured | Request/response notes |
| --- | --- | --- | --- |
| POST | **/api/users/register** | Public | JSON: **name**, **email**, **phone**, **password**. Returns a message string. |
| POST | **/api/users/login** | Public | DTO has **emailOrPhone** and **password**; implementation currently looks up only by email. Returns a JWT string. |
| GET | **/api/users/profile/{userId}** | USER or ADMIN | Returns a **User** entity by arbitrary ID. Service does not enforce owner access; entity includes **passwordHash**. |
| POST | **/api/drivers/register** | Public | JSON: **name**, **phone**, **licenseNumber**, **vehicleDetails**, **password**. Returns a message string. |
| POST | **/api/drivers/login** | Public | JSON: **phone**, **password**. Returns a JWT. Current service logs the password and stored hash. |
| GET | **/api/drivers/available** | USER or ADMIN | Returns available **Driver** entities, including sensitive model fields. |
| PUT | **/api/drivers/status/{id}?available=true** | DRIVER or ADMIN | Changes availability by driver ID; service does not verify the caller owns that driver ID. |
| POST | **/api/rides/book** | USER or ADMIN | JSON: **pickupLocation**, **dropoffLocation**. Assigns the first available driver and returns a **Ride**. |
| PUT | **/api/rides/status/{id}?status=ONGOING** | DRIVER or ADMIN | Status must match a **RideStatus** enum value. Caller-to-ride ownership is not checked. |
| GET | **/api/rides/user/rides** | USER or ADMIN | Uses the authenticated principal to find rider records. |
| POST | **/api/payments/process** | USER or ADMIN | JSON: **rideId**, **amount**, **method**. Stores a record with status **SUCCESS**; does not charge a payment method. |
| GET | **/api/payments/receipt/{rideId}** | USER or ADMIN | Reads by ride ID. Service does not verify the current user owns the ride. |
| POST | **/api/ratings** | USER, DRIVER, or ADMIN | JSON: **rideId**, **score**, optional **comments**. Service checks the rider associated with the ride. |
| GET | **/api/ratings/user/{toUserId}** | DRIVER | Returns ratings for the supplied target ID. |

Spring Security also permits **/v3/api-docs/**, **/swagger-ui/**, and **/swagger-ui.html**. OpenAPI configuration source is currently commented out.

## Error behavior

The global exception handler maps custom not-found errors to 404, custom unauthorized errors to 401, access-denied errors to 403, and validation errors to 400. Other exceptions are printed with a stack trace and mapped to 500. Most request DTOs have no validation annotations, and many service errors use generic runtime exceptions.

## Security limits

The role table describes route annotations, not complete authorization. Several endpoints do not verify row ownership, and direct entity responses include sensitive fields. Do not use these routes with real personal, driver, or payment data until ownership and response handling are fixed.
