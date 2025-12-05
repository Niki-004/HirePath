import re

EMAIL_PATTERN = re.compile(
    r"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"
)

def extract_emails(text: str):
    """Extracts and returns unique emails from a given text block."""
    emails = EMAIL_PATTERN.findall(text)
    return list(set(emails))  # remove duplicates

if __name__ == "__main__":
    sample = """
    Contact me at garima.kaushik@example.com or backup email niki04.dev@gmail.com.
    """
    found = extract_emails(sample)
    print("Extracted emails:", found)
