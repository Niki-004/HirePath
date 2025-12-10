# HirePath / JobTracker – REST API Endpoints

Base URL (local development):
```
http://localhost:8080/api/jobs
```

---

## 1️⃣ **GET /api/jobs**  
Fetch all job applications.

### ✔ Example Response
```json
[
  {
    "id": 1,
    "company": "Google",
    "position": "Software Engineer Intern",
    "jobUrl": "https://careers.google.com/job/123",
    "status": "APPLIED",
    "appliedDate": "2025-11-15",
    "notes": "Referred by John",
    "daysSinceApplied": 5
  }
]
```

---

## 2️⃣ **GET /api/jobs/{id}**  
Fetch a single job by ID.

### ✔ Example
```
GET /api/jobs/1
```

### ✔ Example Response
```json
{
  "id": 1,
  "company": "Google",
  "position": "Software Engineer Intern",
  "jobUrl": "https://careers.google.com/job/123",
  "status": "APPLIED",
  "appliedDate": "2025-11-15",
  "notes": "Referred by John",
  "daysSinceApplied": 5
}
```

---

## 3️⃣ **POST /api/jobs**  
Create a new job application.

### ✔ Request Body
```json
{
  "company": "Amazon",
  "position": "SDE Intern",
  "jobUrl": "https://amazon.jobs/job/xyz",
  "status": "APPLIED",
  "appliedDate": "2025-12-05",
  "notes": "Applied via LinkedIn"
}
```

### ✔ Example Response
```json
{
  "id": 2,
  "company": "Amazon",
  "position": "SDE Intern",
  "status": "APPLIED"
}
```

---

## 4️⃣ **PUT /api/jobs/{id}**  
Update an existing job application.

### ✔ Request Body
```json
{
  "company": "Meta",
  "position": "Front-End Intern",
  "jobUrl": "https://meta.apply/job",
  "status": "INTERVIEW",
  "appliedDate": "2025-12-01",
  "notes": "Tech screen scheduled next week"
}
```

---

## 5️⃣ **DELETE /api/jobs/{id}**  
Delete a job application.

### ✔ Example
```
DELETE /api/jobs/7
Response: 204 No Content
```

---

## 6️⃣ **GET /api/jobs/{id}/suggestion**  
Get an AI-style job recommendation message based on job status.

### ✔ Example Response
```json
{
  "suggestion": "Follow up in 5-7 days if you haven’t heard back."
}
```

---

### ✅ Notes
- All dates follow ISO format: `YYYY-MM-DD`
- `daysSinceApplied` is automatically calculated
- CORS enabled → frontend can call these endpoints
