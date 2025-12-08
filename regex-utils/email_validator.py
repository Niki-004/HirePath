import re

EMAIL_PATTERN = re.compile(
    r"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
)

def is_valid_email(email: str) -> bool:
    """Returns True if email is valid, else False."""
    return EMAIL_PATTERN.match(email) is not None


if __name__ == "__main__":
    test_emails = [
        "garima@example.com",
        "invalid-email",
        "test.user@domain.co",
        "abc@xyz",
    ]

    for email in test_emails:
        print(email, "→", "VALID" if is_valid_email(email) else "INVALID")
