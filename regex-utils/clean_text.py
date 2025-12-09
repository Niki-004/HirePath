import re

def clean_text(text: str) -> str:
    """
    Cleans job descriptions or resume text.
    Removes HTML tags, special characters, and extra spaces.
    """

    if not text:
        return ""

    # Remove HTML tags like <div>, <p>, <br>
    text = re.sub(r"<[^>]+>", " ", text)

    # Replace non-alphanumeric characters with spaces
    text = re.sub(r"[^a-zA-Z0-9]+", " ", text)

    # Collapse multiple spaces into one
    text = re.sub(r"\s+", " ", text).strip()

    return text


if __name__ == "__main__":
    sample = """
        <div>Software Engineer!!! $$$</div>
        Required skills: Java, SQL, React <br> Apply Now!
    """

    print(clean_text(sample))
