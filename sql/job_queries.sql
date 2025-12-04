-- JobTracker SQL Queries
-- Day 9

-- 1. Count applications by status
SELECT status, COUNT(*) AS total
FROM jobs
GROUP BY status;

-- 2. Jobs applied this month
SELECT *
FROM jobs
WHERE applied_date >= DATE_TRUNC('month', CURRENT_DATE);

-- 3. Top companies you applied to
SELECT company, COUNT(*) AS applications
FROM jobs
GROUP BY company
ORDER BY applications DESC;

-- 4. Jobs missing notes (incomplete applications)
SELECT *
FROM jobs
WHERE notes IS NULL OR notes = '';
