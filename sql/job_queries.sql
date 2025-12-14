-- JobTracker SQL Queries
-- Day 9

-- 1. Count applications by status
SELECT status, COUNT(*) AS total
FROM jobs
GROUP BY status;

-- 2. Jobs applied this month
SELECT *
-- JobTracker Analytics Queries
-- Table assumed: jobs
-- Columns: id, company, position, job_url, status, applied_date, notes

-- 1) Total jobs
SELECT COUNT(*) AS total_jobs
FROM jobs;

-- 2) Count by status
SELECT status, COUNT(*) AS count
FROM jobs
GROUP BY status
ORDER BY count DESC;

-- 3) Count by company (top 10)
SELECT company, COUNT(*) AS count
FROM jobs
GROUP BY company
ORDER BY count DESC
LIMIT 10;

-- 4) Jobs applied in the last 30 days
SELECT *
FROM jobs
WHERE applied_date >= CURRENT_DATE - INTERVAL '30 days'
ORDER BY applied_date DESC;

-- 5) Applications older than 14 days (follow-up list)
SELECT id, company, position, status, applied_date
FROM jobs
WHERE applied_date IS NOT NULL
  AND applied_date <= CURRENT_DATE - INTERVAL '14 days'
  AND status IN ('APPLIED', 'IN_REVIEW')
ORDER BY applied_date ASC;

-- 6) Weekly applied count (last 8 weeks)
SELECT DATE_TRUNC('week', applied_date) AS week_start,
       COUNT(*) AS applied_count
FROM jobs
WHERE applied_date IS NOT NULL
  AND applied_date >= CURRENT_DATE - INTERVAL '56 days'
GROUP BY week_start
ORDER BY week_start DESC;

-- 7) Interview rate (percent)
SELECT
  ROUND(
    100.0 * SUM(CASE WHEN status = 'INTERVIEW' THEN 1 ELSE 0 END) / NULLIF(COUNT(*), 0),
    2
  ) AS interview_rate_percent
FROM jobs;

-- 8) Offer rate (percent)
SELECT
  ROUND(
    100.0 * SUM(CASE WHEN status = 'OFFER' THEN 1 ELSE 0 END) / NULLIF(COUNT(*), 0),
    2
  ) AS offer_rate_percent
FROM jobs;
