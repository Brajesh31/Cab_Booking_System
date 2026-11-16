# 🧪 API Request Examples

Examples use local-only placeholder values. They describe request shapes, not a production contract.

## Rider registration

~~~http
POST http://localhost:8080/api/users/register
Content-Type: application/json

{
  "name": "Demo Rider",
  "email": "rider@example.test",
  "phone": "9000000001",
  "password": "DO_NOT_REUSE_THIS_EXAMPLE"
}
~~~

## Rider login

~~~http
POST http://localhost:8080/api/users/login
Content-Type: application/json

{
  "emailOrPhone": "rider@example.test",
  "password": "DO_NOT_REUSE_THIS_EXAMPLE"
}
~~~

The current service lookup uses email despite the DTO property name `emailOrPhone`. Verify source before relying on phone login.

## Driver registration

~~~http
POST http://localhost:8080/api/drivers/register
Content-Type: application/json

{
  "name": "Demo Driver",
  "phone": "9000000002",
  "licenseNumber": "DEMO-LICENSE-NOT-REAL",
  "vehicleDetails": "Demo vehicle",
  "password": "DO_NOT_REUSE_THIS_EXAMPLE"
}
~~~

## Book a ride

~~~http
POST http://localhost:8080/api/rides/book
Authorization: Bearer <RIDER_JWT>
Content-Type: application/json

{
  "pickupLocation": "Demo pickup location",
  "dropoffLocation": "Demo drop-off location"
}
~~~

The service currently assigns the first available driver and uses a random fare. Never use real addresses with this prototype.

## Update a ride status

~~~http
PUT http://localhost:8080/api/rides/status/1?status=ONGOING
Authorization: Bearer <DRIVER_JWT>
~~~

The current implementation does not verify that the authenticated driver is assigned to ride ID 1.

## Payment record

~~~http
POST http://localhost:8080/api/payments/process
Authorization: Bearer <RIDER_JWT>
Content-Type: application/json

{
  "rideId": 1,
  "amount": 100.0,
  "method": "CASH"
}
~~~

This only demonstrates the current record-creation contract. It is not a real payment transaction.

## Example safety rules
- Never use real phone numbers, emails, passwords, driver license identifiers, tokens, or addresses.
- Never commit a JWT value or populated environment file.
- Confirm DTO fields and enum values in Java source before changing examples.
- Document security limitations beside the endpoint.
