# HirePath – JobTracker Backend

This is the backend API for my HirePath job application tracker, built with **Spring Boot 3**, **Java 17**, **Gradle**, **Spring Data JPA**, and **Flyway**.

## Tech Stack

- Java 17  
- Spring Boot 3 (Web, Data JPA)  
- H2 in-memory DB (dev)  
- PostgreSQL (later for prod)  
- Flyway for DB migrations  
- JUnit + Spring Boot Test + MockMvc for testing  

## Current Features (Day 7)

- `/health` – simple health endpoint
- `/api/jobs`  
  - `GET /api/jobs` – list all jobs  
  - `GET /api/jobs/{id}` – get one job  
  - `POST /api/jobs` – create job  
  - `PUT /api/jobs/{id}` – update job  
  - `DELETE /api/jobs/{id}` – delete job  
- `GET /api/jobs/{id}/suggestion` – returns a suggestion string based on job status  
- `Job` entity with fields like company, position, status, jobUrl, appliedDate, notes  
- Flyway migration `V1__create_jobs_table.sql`
- Unit tests for `Job` and suggestion logic  
- Controller test for suggestion endpoint

## How to Run

```bash
cd backend-java
./gradlew clean bootRun

