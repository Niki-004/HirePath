import re

def clean_job_title(title: str) -> str:
    """Clean job title by removing unwanted symbols, parentheses, and repeated spaces."""

    if not title:
        return ""

    # Remove content inside parentheses (location, remote tags, etc.)
    title = re.sub(r"\(.*?\)", "", title)

    # Remove non-letter characters except spaces
    title = re.sub(r"[^a-zA-Z0-9 ]+", "", title)

    # Replace multiple spaces with one
    title = re.sub(r"\s+", " ", title).strip()

    # Convert to title case
    return title.title()


if __name__ == "__main__":
    samples = [
        "Software Engineer!!! (Remote)",
        "Senior Developer - Toronto!!!",
        "Data Analyst   (Contract)",
        "FULL STACK DEVELOPER***"
    ]

    print("\n🧹 Cleaned Job Titles\n----------------------")
    for s in samples:
        print(f"Before: {s}")
        print(f"After:  {clean_job_title(s)}\n")
