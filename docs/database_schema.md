CREATE TABLE jobs (
    id BIGSERIAL PRIMARY KEY,
    company VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    job_url TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'APPLIED',
    applied_date DATE,
    notes TEXT
);

