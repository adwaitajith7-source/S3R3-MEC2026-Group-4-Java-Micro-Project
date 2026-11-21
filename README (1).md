# VERITAS — Digital Grievance Management Platform

Java 17 / Spring Boot 3 / Thymeleaf / Spring Security / JPA. Responsive UI with citizen and administrator experiences.

## Quick start
1. Install Java 17+ and Maven.
2. From this directory, run `mvn spring-boot:run`.
3. Open http://localhost:8080.
4. Demo credentials:
   - Citizen: `citizen` / `Citizen@123`
   - Admin: `admin` / `Admin@123`

Default uses embedded H2 database (file-backed). The H2 console is enabled at `/h2-console` for local development only.
For MySQL, uncomment the MySQL profile settings in `application-mysql.properties` and run with `--spring.profiles.active=mysql`; set environment variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`.

## Features
- Role-based access (citizen / administrator) with Spring Security
- Login and logout
- Citizen dashboard and complaint submission
- Citizen complaint list and tracking
- Administrator complaint management, filters, status updates and remarks
- Analytics endpoint and dashboard summary
- Responsive sidebar, mobile navigation, status badges, charts drawn in canvas
- Demo users and sample complaints seeded when database is empty

## Security note
Demo credentials and local H2 console are for local development. Change credentials, configure CSRF-aware form flows, use HTTPS, review validation, audit logging, backups, rate limiting and deployment secrets before production. The sample login form is protected by Spring Security. Never expose the H2 console publicly.
