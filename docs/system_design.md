# HirePath / JobTracker – System Design Overview

This document explains the backend architecture, components, and data flow used in the JobTracker application.

---

## 1. Architecture Summary

**Type:** RESTful backend  
**Framework:** Spring Boot (Java)  
**Database:** PostgreSQL (production) / H2 (local development)  
**Migrations:** Flyway  
**Build Tool:** Gradle  
**Other Tools:** Python keyword ranking, regex validation

### 🎯 Goal  
Track job applications, statuses, notes, and generate suggestions.

---

## 2. System Components

### ✅ **1. Controller Layer**
Handles API requests.

Main controllers:
- `JobController` → CRUD for jobs
- `SuggestionController` → returns suggestion text for a job

---

### ✅ **2. Service Layer**
Contains business logic.

Services:
- `JobSuggestionService` → generates recommendation based on job status

---

### ✅ **3. Repository Layer**
Handles database operations using JPA.

Main repositories:
- `JobRepository` → CRUD operations on `jobs` table

---

### ✅ **4. Database Layer**
Entity: `Job.java`

Fields:
- id  
- company  
- position  
- jobUrl  
- status  
- appliedDate  
- notes  
- daysSinceApplied (computed getter)

---

## 3. Data Flow Diagram (Text Version)

```
[Client / Frontend]
        |
        v
HTTP Request (REST API)
        |
        v
[Controller Layer]
        |
        v
[Service Layer]
        |
        v
[Repository Layer]
        |
        v
[PostgreSQL / H2 Database]
```

---

## 4. Migration System (Flyway)

Migration files stored in:
```
src/main/resources/db/migration/
```

Example migration:  
`V1__create_jobs_table.sql`

This ensures database schema is versioned and consistent.

---

## 5. Suggestion Logic

Based on `status` the system returns:

- **APPLIED** → Follow-up timeline  
- **IN_REVIEW** → Wait message  
- **INTERVIEW** → Preparation tips  
- **REJECTED** → Motivation + next steps  
- **OFFER** → Negotiation tips  

All handled inside:
`JobSuggestionService.java`

---

## 6. Local Development Environment

### ```./gradlew bootRun```
Runs server on:
```
http://localhost:8080
```

---

## 7. API Contracts

Defined fully in  
```
docs/api_endpoints.md
```

---

## 8. Folder Structure Summary

```
backend-java/
 ├── src/main/java/com/jobtracker
 │      ├── controller/
 │      ├── service/
 │      ├── model/
 │      ├── repository/
 │      └── JobTrackerApplication.java
 ├── src/main/resources/db/migration/
 ├── test/
 ├── gradle/
 ├── build.gradle
docs/
 ├── api_endpoints.md
 └── system_design.md
sql/
python-tools/
regex-utils/
```

---

## 9. Future Improvements

- Add authentication (JWT)
- Add analytics dashboard
- Add resume PDF parsing
- Add ML scoring system

---

# ✔ End of System Design File
