import re
from pathlib import Path

JOB_URL_PATTERN = re.compile(
    r"^https?://[a-zA-Z0-9._-]+/(jobs|careers|careers/|jobs/).*$"
)


def is_valid_job_url(url: str) -> bool:
    return JOB_URL_PATTERN.match(url.strip()) is not None


def validate_file(path: str) -> None:
    file_path = Path(path)
    if not file_path.exists():
        print(f"File not found: {file_path}")
        return

    with file_path.open("r", encoding="utf-8") as f:
        lines = [line.strip() for line in f if line.strip()]

    for url in lines:
        print(url, "→", "VALID" if is_valid_job_url(url) else "INVALID")


if __name__ == "__main__":
    import sys

    if len(sys.argv) != 2:
        print("Usage: python url_batch_validator.py <path-to-url-list.txt>")
        sys.exit(1)

    validate_file(sys.argv[1])
