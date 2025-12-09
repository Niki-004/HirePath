# HirePath / JobTracker – Architecture Overview

## 1. High-Level Overview

HirePath is a personal job application tracker that helps you:
- Store job applications (company, role, URL, status, notes, date applied)
- Track progress (applied, in review, interview, rejected, offer)
- Run analytics with SQL
- Use small Python/regex tools to clean data and score resumes

Main pieces:
- **Backend**: Spring Boot (Java, Gradle)
- **Database**: H2 for dev, PostgreSQL planned for production
- **Migrations**: Flyway (SQL scripts)
- **Helper tools**: Python scripts + Regex utilities
- **(Future)** Frontend web UI

---

## 2. Backend (Spring Boot, Java)

**Location:** `backend-java/`

Key parts:
- `src/main/java/com/jobtracker/JobTrackerApplication.java`  
  - Spring Boot entry point (`main` class).

- `src/main/java/com/jobtracker/model/Job.java`  
  - JPA entity mapped to the `jobs` table.
  - Fields: `id`, `company`, `position`, `jobUrl`, `status`, `appliedDate`, `notes`.
  - Helper: `getDaysSinceApplied()` to compute how old the application is.

- `src/main/java/com/jobtracker/repository/JobRepository.java`  
  - Extends `JpaRepository<Job, Long>`.
  - Gives CRUD and query methods for `Job`.

- `src/main/java/com/jobtracker/controller/JobController.java`  
  - REST API under `/api/jobs`.
  - Endpoints:
    - `GET /api/jobs` – list all jobs
    - `GET /api/jobs/{id}` – get one job
    - `POST /api/jobs` – create new job
    - `PUT /api/jobs/{id}` – update job
    - `DELETE /api/jobs/{id}` – delete job

- `src/main/java/com/jobtracker/service/JobSuggestionService.java`  
  - Generates human-friendly suggestions based on status  
    (`APPLIED`, `IN_REVIEW`, `INTERVIEW`, `REJECTED`, `OFFER`).

---

## 3. Database & Migrations (Flyway + SQL)

**Location:**  
- `backend-java/src/main/resources/application.yml`  
- `backend-java/src/main/resources/db/migration/`

Currently using **H2 in-memory** for local dev.  
Flyway runs automatically on startup and looks for `V__*.sql` files.

Example migration:
- `V1__create_jobs_table.sql` – creates the `jobs` table used by `Job` entity.

Extra analytics queries live in:
- `sql/job_queries.sql` – helpful SELECTs for:
  - Counts by status
  - Group by company
  - Old applications (stale)

- `sql/job_data_report.sql` – more “report-style” queries.

---

## 4. Python Tools

**Location:** `python-tools/`

- `resume_keyword_ranker.py`  
  - Reads a resume text and a list of keywords.
  - Ranks which keywords appear and how often.
  - Idea: quickly check how well a resume matches a job posting.

- `job_csv_stats.py`  
  - Reads a CSV of job applications.
  - Prints quick stats (total jobs, by status, by company).

These can be used manually from the command line or integrated later into the app.

---

## 5. Regex & URL Utilities

**Location:** `regex-utils/`

- `job_url_validator.py`  
  - Uses regex to check if a job URL looks valid.
  - Helpful before saving a job to avoid broken links.

- `clean_url.py`  
  - Removes tracking parameters like `utm_source`, `fbclid`, `gclid`, `ref` from URLs.
  - Returns a clean job posting URL for consistent storage.

---

## 6. How Things Fit Together

1. **Backend API**  
   - User (or future frontend) calls `/api/jobs` endpoints.
   - Data is stored in the `jobs` table via JPA.

2. **Database migrations**  
   - Flyway ensures the `jobs` table exists and matches the entity.

3. **SQL scripts (`sql/`)**  
   - Let you run reporting/analytics directly on the database.

4. **Python tools (`python-tools/`)**  
   - Help with resume analysis and CSV stats around the same job data.

5. **Regex tools (`regex-utils/`)**  
   - Clean and validate job URLs before storing them.

This gives a small full “ecosystem” around job applications:
- Store → Track → Analyze → Clean data → Improve resumes.
