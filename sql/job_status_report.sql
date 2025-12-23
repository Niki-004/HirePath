-- Job Status Analytics Report
-- Purpose: Generate useful summaries for job applications

-- 1. Total number of job applications
SELECT COUNT(*) AS total_jobs
FROM jobs;

-- 2. Job count by status
SELECT status, COUNT(*) AS count
FROM jobs
GROUP BY status
ORDER BY count DESC;

-- 3. Job count by company
SELECT company, COUNT(*) AS applications
FROM jobs
GROUP BY company
ORDER BY applications DESC;

-- 4. Applications older than 14 days
SELECT company, position, status, applied_date
FROM jobs
WHERE applied_date <= CURRENT_DATE - INTERVAL '14 days'
ORDER BY applied_date;

-- 5. Jobs currently in interview stage
SELECT company, position, applied_date
FROM jobs
WHERE status = 'INTERVIEW'
ORDER BY applied_date;
