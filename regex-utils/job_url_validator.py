# Day 9 – Job URL Validator using Regex

import re

URL_PATTERN = re.compile(
    r"^(https?://)?(www\.)?[A-Za-z0-9-]+\.[A-Za-z]{2,}(/.*)?$"
)

def is_valid_job_url(url):
    return bool(URL_PATTERN.match(url))


if __name__ == "__main__":
    test_urls = [
        "https://amazon.jobs/en/jobs/12345",
        "www.linkedin.com/jobs/view/55",
        "bad-url",
        "http:/wrong.com"
    ]

    for u in test_urls:
        print(u, "=>", is_valid_job_url(u))
