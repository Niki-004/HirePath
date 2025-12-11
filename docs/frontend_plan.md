# 🎨 Frontend Plan – JobTracker (HirePath)

This document describes the planned frontend for the JobTracker/HirePath application.  
The frontend will be built as a separate project (e.g., React or simple HTML/JS) and will talk to the backend via REST APIs.

---

## 🧱 1. Tech Stack (Planned)

- **Framework**: React (Create React App or Vite) – beginner friendly
- **Language**: JavaScript (or TypeScript later)
- **Styling**: CSS modules or Tailwind (optional)
- **HTTP client**: `fetch` (built-in) or `axios`
- **Backend API base URL**: `http://localhost:8080/api`

---

## 📚 2. Main Pages

### 2.1 Home / Dashboard – `/`

- Shows **summary of total applications**
  - Count of jobs per status (APPLIED, IN_REVIEW, INTERVIEW, REJECTED, OFFER)
- Possible widgets:
  - “Total jobs applied”
  - “Interviews this week”
  - “Offers received”

Calls:
- `GET /api/jobs` to load all jobs.
- Basic filtering by status on the frontend.

---

### 2.2 Job List – `/jobs`

- Table or cards showing all job applications:

Columns:
- Company  
- Position  
- Status  
- Applied Date  
- Actions: View / Edit / Delete

Actions:
- **View** → go to `/jobs/:id`
- **Edit** → go to `/jobs/:id/edit`
- **Delete** → confirm then call `DELETE /api/jobs/{id}` and refresh list

API:
- `GET /api/jobs`
- `DELETE /api/jobs/{id}`

---

### 2.3 Add New Job – `/jobs/new`

Form fields:
- Company (text, required)  
- Position (text, required)  
- Job URL (text, optional, validated with regex)  
- Status (select: APPLIED, IN_REVIEW, INTERVIEW, REJECTED, OFFER; default APPLIED)  
- Applied Date (date picker)  
- Notes (textarea)

On submit:
- Call `POST /api/jobs` with JSON body
- On success → redirect to `/jobs`

Validation:
- Company and Position must not be empty  
- URL should be a valid URL if provided (can reuse regex from `regex-utils/job_url_validator.py` idea)

---

### 2.4 View Job Details – `/jobs/:id`

Shows all fields for a single job:

- Company  
- Position  
- Job URL (clickable link)  
- Status  
- Applied Date  
- Notes  
- Days since applied (using `daysSinceApplied` returned by backend if available)

Extra:
- Button: **“Get Suggestion”**  
  - Calls `GET /api/jobs/{id}/suggestion`  
  - Displays returned suggestion text (e.g., follow up, apply more, etc.)

API:
- `GET /api/jobs/{id}`
- `GET /api/jobs/{id}/suggestion`

---

### 2.5 Edit Job – `/jobs/:id/edit`

Same fields as “Add New Job” but **pre-filled** with existing values.

Actions:
- On save → `PUT /api/jobs/{id}`  
- On cancel → back to `/jobs/:id`

---

## 🧩 3. React Component Structure (Idea)

```text
src/
  App.js
  components/
    Layout/
      Navbar.jsx
      Sidebar.jsx (optional)
    jobs/
      JobList.jsx
      JobForm.jsx       // used for both create & edit
      JobDetails.jsx
      JobStatusBadge.jsx
