# HirePath / JobTracker – Sequence Diagram

This diagram shows how a **client request flows through the backend** when creating or retrieving a job application.

---

## 1. Create Job Request (POST /api/jobs)

Text-based sequence diagram:

```
Client
  |
  | POST /api/jobs
  v
JobController
  |
  | validate + set default status
  v
JobRepository
  |
  | save(Job)
  v
Database (jobs table)
  |
  | return saved entity
  v
JobController
  |
  | HTTP 201 Created + JSON response
  v
Client
```

---

## 2. Get All Jobs (GET /api/jobs)

```
Client
  |
  | GET /api/jobs
  v
JobController
  |
  | jobRepository.findAll()
  v
JobRepository
  |
  | SELECT * FROM jobs
  v
Database
  |
  v
JobController
  |
  | return List<Job>
  v
Client
```

---

## 3. Job Suggestion Request (GET /api/jobs/{id}/suggestion)

```
Client
  |
  | GET /api/jobs/12/suggestion
  v
SuggestionController
  |
  | jobRepository.findById(12)
  v
JobRepository
  |
  | SELECT * FROM jobs WHERE id = 12
  v
Database
  |
  v
SuggestionController
  |
  | suggestionService.generateSuggestion(job)
  v
JobSuggestionService
  |
  | returns suggestion string
  v
SuggestionController
  |
  | HTTP 200 OK + {"suggestion": "..."}
  v
Client
```

---

## Purpose of This Document

- Helps understand **flow of execution**
- Helps during **interviews**
- Helps collaborators understand the backend

---

# ✔ End of Sequence Diagram
