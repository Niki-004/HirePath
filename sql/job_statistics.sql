-- Count applications by status
SELECT status, COUNT(*) AS total
FROM jobs
GROUP BY status
ORDER BY total DESC;

-- Find applications older than 30 days
SELECT *
FROM jobs
WHERE applied_date <= (CURRENT_DATE - INTERVAL '30 days');

-- Company with most applications
SELECT company, COUNT(*) AS total
FROM jobs
GROUP BY company
ORDER BY total DESC
LIMIT 1;

-- Positions you applied most to
SELECT position, COUNT(*) AS total
FROM jobs
GROUP BY position
ORDER BY total DESC;
