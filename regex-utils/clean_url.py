import re
from urllib.parse import urlparse, parse_qs, urlencode, urlunparse


def clean_job_url(url: str) -> str:
    """
    Removes unnecessary tracking parameters like:
    - utm_source
    - utm_medium
    - utm_campaign
    - fbclid
    - gclid
    - ref

    Returns a clean job posting URL.
    """
    if not url or not isinstance(url, str):
        return ""

    parsed = urlparse(url)
    query_params = parse_qs(parsed.query)

    # Parameters we want to REMOVE
    blacklist = {"utm_source", "utm_medium", "utm_campaign", "utm_term",
                 "utm_content", "fbclid", "gclid", "ref"}

    # Filter out blacklisted params
    filtered_params = {k: v for k, v in query_params.items() if k not in blacklist}

    clean_query = urlencode(filtered_params, doseq=True)

    cleaned = urlunparse((
        parsed.scheme,
        parsed.netloc,
        parsed.path,
        parsed.params,
        clean_query,
        parsed.fragment
    ))

    return cleaned


if __name__ == "__main__":
    # Example demo
    example = (
        "https://jobs.company.com/apply?utm_source=linkedin&utm_medium=social&ref=abc123&id=9023"
    )

    print("Original:", example)
    print("Cleaned: ", clean_job_url(example))
