-- ===========================================
-- DAY 9 - DATA CLEANUP & TRANSFORMATION SQL
-- ===========================================

-- 1️⃣ Standardize all status values to uppercase
UPDATE jobs
SET status = UPPER(status);

-- 2️⃣ Fix missing applied dates (if NULL → set to today's date)
UPDATE jobs
SET applied_date = CURRENT_DATE
WHERE applied_date IS NULL;

-- 3️⃣ Flag old applications (> 60 days)
ALTER TABLE jobs
ADD COLUMN IF NOT EXISTS stale_application BOOLEAN DEFAULT FALSE;

UPDATE jobs
SET stale_application = TRUE
WHERE applied_date < CURRENT_DATE - INTERVAL '60 days';

-- 4️⃣ Remove leading/trailing spaces in text fields
UPDATE jobs
SET company = TRIM(company),
    position = TRIM(position),
    job_url = TRIM(job_url),
    notes = TRIM(notes);

-- 5️⃣ Delete duplicate job entries (same company + position + url)
DELETE FROM jobs a
USING jobs b
WHERE a.id > b.id
  AND a.company = b.company
  AND a.position = b.position
  AND a.job_url = b.job_url;

-- 6️⃣ Summary report check
SELECT status, COUNT(*) AS count
FROM jobs
GROUP BY status
ORDER BY count DESC;
