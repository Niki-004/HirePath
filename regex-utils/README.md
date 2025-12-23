# Regex Utilities – JobTracker

This folder contains small utilities built using regular expressions (regex)
to validate and clean job-related data.

These scripts support the JobTracker project by improving data quality
before it reaches the backend.

---

## 📄 job_url_validator.py

**Purpose:**  
Validates job application URLs to ensure they follow proper formatting.

**What it checks:**
- Valid HTTP / HTTPS URLs
- Prevents malformed job links
- Helps maintain clean job records

**Example Usage:**
```bash
python job_url_validator.py
