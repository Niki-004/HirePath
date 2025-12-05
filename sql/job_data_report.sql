-- ==========================================================
-- JobTracker – Data Analysis Report SQL
-- Author: Garima (Niki-004)
-- Description:
--   Advanced SQL queries to analyze job application patterns.
-- ==========================================================


------------------------------
-- 1. Total applications
------------------------------
SELECT COUNT(*) AS total_applications
FROM jobs;


------------------------------
-- 2. Count by status
------------------------------
SELECT status, COUNT(*) AS count
FROM jobs
GROUP BY status
ORDER BY count DESC;


------------------------------
-- 3. Number of companies applied to
------------------------------
SELECT COUNT(DISTINCT company) AS unique_companies
FROM jobs;


------------------------------
-- 4. Most applied companies
------------------------------
SELECT company, COUNT(*) AS applications
FROM jobs
GROUP BY company
ORDER BY applications DESC
LIMIT 5;


------------------------------
-- 5. Applications older than 14 days 
------------------------------
SELECT *
FROM jobs
WHERE applied_date <= CURRENT_DATE - INTERVAL '14 days'
ORDER BY applied_date ASC;


------------------------------
-- 6. Average time since application
------------------------------
SELECT AVG(CURRENT_DATE - applied_date) AS avg_days_waiting
FROM jobs
WHERE applied_date IS NOT NULL;


------------------------------
-- 7. Job URLs missing
------------------------------
SELECT id, company, position
FROM jobs
WHERE job_url IS NULL OR job_url = ''
ORDER BY id;


------------------------------
-- 8. Notes sentiment check (basic)
------------------------------
SELECT id, notes
FROM jobs
WHERE notes ILIKE '%stressed%' 
   OR notes ILIKE '%worried%' 
   OR notes ILIKE '%excited%' 
ORDER BY id;


------------------------------
-- 9. Find duplicate company + position entries
------------------------------
SELECT company, position, COUNT(*) AS duplicates
FROM jobs
GROUP BY company, position
HAVING COUNT(*) > 1;


------------------------------
-- 10. Summary of pipeline
------------------------------
SELECT
    SUM(CASE WHEN status='APPLIED' THEN 1 ELSE 0 END)       AS applied,
    SUM(CASE WHEN status='IN_REVIEW' THEN 1 ELSE 0 END)     AS review,
    SUM(CASE WHEN status='INTERVIEW' THEN 1 ELSE 0 END)     AS interview,
    SUM(CASE WHEN status='OFFER' THEN 1 ELSE 0 END)         AS offers,
    SUM(CASE WHEN status='REJECTED' THEN 1 ELSE 0 END)      AS rejected
FROM jobs;
