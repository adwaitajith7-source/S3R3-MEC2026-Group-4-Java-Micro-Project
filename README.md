# VERITAS — Java grievance management platform

A starter Spring Boot application with role-based access (CITIZEN / ADMIN), BCrypt password hashing, citizen complaint creation/list/detail, and admin complaint listing/status updates.


## Requirements
- Java 17+
- Maven 3.8+
- MySQL 8+

## Configure database
Create database `veritas` or let the configured URL create it (MySQL account must have permission). Set environment variables:

```bash
export DB_URL='jdbc:mysql://localhost:3306/veritas?createDatabaseIfNotExist=true'
export DB_USER='root'
export DB_PASSWORD='your-db-password'
export VERITAS_ADMIN_USER='admin'
export VERITAS_ADMIN_PASSWORD='set-a-strong-admin-password'
```

The default seeded administrator is `admin` / `ChangeMe_123!` only if environment variables are not set. **Change this before running outside local development.**

## Run
```bash
mvn spring-boot:run
```
Open http://localhost:8080 and login. New citizen accounts can be created via the registration API.


## API overview
All API routes use Spring Security session authentication (form login) and CSRF protection. Basic REST clients must obtain a session cookie and CSRF token for state-changing calls.


### Citizen registration (public)
`POST /api/auth/register` JSON: `{"username":"citizen1","password":"password123"}`

### Citizen endpoints (authenticated)
- `POST /api/complaints` JSON: `{"title":"Road damaged","description":"Large pothole...","category":"Roads"}`
- `GET /api/complaints` — your complaint list
- `GET /api/complaints/{id}` — view one of your own complaints


### Admin endpoints (ADMIN role)
- `GET /api/admin/complaints` — all complaints
- `PATCH /api/admin/complaints/{id}` JSON: `{"status":"IN_PROGRESS","remarks":"Assigned to field team"}`

Valid status values: `PENDING`, `IN_PROGRESS`, `RESOLVED`, `REJECTED`.


## Roles & access
- Citizen registration always assigns `CITIZEN` role. Role is not accepted from client input.
- `/api/admin/**` requires `ADMIN` role.
- `/api/complaints/**` requires authentication. Complaint queries filter to the authenticated username.
- Passwords are stored as BCrypt hashes.


## Notes for a production deployment
- Replace default administrator credentials immediately.
- Configure HTTPS and secure cookie settings behind a TLS proxy.
- Add rate limiting, audit logging, email/SMS notifications, file-upload validation, pagination, and monitoring.
- Use migrations (Flyway/Liquibase) rather than Hibernate auto-update in production.
- Review complaint data retention and privacy requirements.
