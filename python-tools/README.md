# Python Tools – JobTracker

This folder contains Python utilities that support the JobTracker project.

These tools are designed to help with resume analysis, job matching,
and automation outside the core Java backend.

---

## 📄 resume_keyword_ranker.py

**Purpose:**  
Analyzes a resume and ranks keyword relevance based on job descriptions.

**Features:**
- Counts keyword frequency
- Helps identify missing skills
- Useful for ATS (Applicant Tracking System) optimization

**Example Usage:**
```bash
python resume_keyword_ranker.py resume.txt keywords.txt
