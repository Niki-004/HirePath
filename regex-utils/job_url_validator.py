"""
Job URL Validator (Regex)
-------------------------
Validates that a job URL looks like a real http/https link,
and optionally checks common job-site patterns.

Run:
  python3 regex-utils/job_url_validator.py "https://amazon.jobs/en/jobs/123"
"""

import re
import sys

# Basic URL validation (http/https, domain, optional path/query)
BASIC_URL_REGEX = re.compile(
    r"^https?://"
    r"([a-z0-9-]+\.)+[a-z]{2,}"      # domain
    r"(:\d{1,5})?"                   # optional port
    r"(/[^ \t\r\n]*)?$",             # optional path/query
    re.IGNORECASE
)

# Optional: common job sites (extend anytime)
COMMON_JOB_SITES_REGEX = re.compile(
    r"(linkedin\.com/jobs|indeed\.com|greenhouse\.io|lever\.co|workday\.com|amazon\.jobs|careers\.google\.com)",
    re.IGNORECASE
)

def is_valid_url(url: str) -> bool:
    return bool(BASIC_URL_REGEX.match(url.strip()))

def is_common_job_site(url: str) -> bool:
    return bool(COMMON_JOB_SITES_REGEX.search(url))

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python3 regex-utils/job_url_validator.py <url>")
        sys.exit(1)

    url = sys.argv[1].strip()

    valid = is_valid_url(url)
    common = is_common_job_site(url) if valid else False

    print("URL:", url)
    print("Valid:", valid)
    print("Common job site:", common)
