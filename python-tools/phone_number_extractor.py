import re

# Matches formats like:
# 123-456-7890
# (123) 456-7890
# 1234567890
# +1 123 456 7890
PHONE_PATTERN = re.compile(
    r"""
    (\+?\d{1,3}[\s\-\.]?)?          # Country code (optional)
    \(?\d{3}\)?[\s\-\.]?            # Area code with optional parentheses
    \d{3}[\s\-\.]?\d{4}             # Local number
    """,
    re.VERBOSE
)

def extract_phone_numbers(text: str):
    """Extract unique phone numbers and return cleaned versions."""
    matches = PHONE_PATTERN.findall(text)
    
    cleaned = set()
    for m in matches:
        number = re.sub(r"[^\d+]", "", m)  # remove non-numeric chars except +
        cleaned.add(number)
    
    return list(cleaned)

if __name__ == "__main__":
    sample = """
    Contact: (647) 555-1234 or +1 416 987 6543 or 905-222-1111.
    """
    print("Extracted numbers:", extract_phone_numbers(sample))
