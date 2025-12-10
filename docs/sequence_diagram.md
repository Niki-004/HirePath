# Sequence Diagram – Job Creation & Suggestion Flow

This document explains the flow of data through the backend using two sequence diagrams:
1. Creating a job (`POST /api/jobs`)
2. Getting a job suggestion (`GET /api/jobs/{id}/suggestion`)

---

# 1. Sequence Diagram – Create Job

```
Client → JobController : POST /api/jobs
JobController → JobRepository : save(job)
JobRepository → Database : INSERT job row
Database → JobRepository : return saved row
JobRepository → JobController : return saved job
JobController → Client : JSON response
```

### Explanation
- Controller receives JSON and converts it into a `Job` object  
- Repository saves it to the DB  
- DB returns the generated ID  
- Controller returns the created job  

---

# 2. Sequence Diagram – Job Suggestion

```
Client → SuggestionController : GET /api/jobs/{id}/suggestion
SuggestionController → JobRepository : findById(id)
JobRepository → Database : SELECT * FROM jobs WHERE id = ?
Database → JobRepository : return job row
SuggestionController → JobSuggestionService : generateSuggestion(job)
JobSuggestionService → SuggestionController : return suggestion text
SuggestionController → Client : JSON { suggestion: "..." }
```

### Explanation
- API receives the job ID  
- Repository fetches it  
- Service generates a suggestion string  
- Controller sends JSON back  

---

# 3. Sequence Diagram Key

| Symbol | Meaning |
|-------|---------|
| →     | Call / request |
| ←     | Return result |
| :     | Method/action description |

---

# End of document
