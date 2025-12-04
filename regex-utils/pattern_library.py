import re

class RegexLibrary:
    """
    A small library of reusable regex patterns used in the JobTracker tools.
    """

    # Matches LinkedIn job URLs
    LINKEDIN_JOB_PATTERN = re.compile(
        r"(https?://)?(www\.)?linkedin\.com/jobs/view/[0-9]+"
    )

    # Matches Indeed job URLs
    INDEED_JOB_PATTERN = re.compile(
        r"(https?://)?(www\.)?indeed\.com/viewjob\?jk=[A-Za-z0-9]+"
    )

    # Basic email validation
    EMAIL_PATTERN = re.compile(
        r"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.(com|net|org|ca|io)"
    )

    # Extracts salary ranges (e.g., "$50k-$70k")
    SALARY_RANGE_PATTERN = re.compile(
        r"\$?\s?([0-9]{2,3})k\s*-\s*\$?\s?([0-9]{2,3})k"
    )

    @staticmethod
    def validate(pattern, text):
        return bool(pattern.search(text))


if __name__ == "__main__":
    print("Testing patterns...")

    print(RegexLibrary.validate(RegexLibrary.EMAIL_PATTERN, "test@example.com"))  # True
    print(RegexLibrary.validate(RegexLibrary.LINKEDIN_JOB_PATTERN,
          "https://www.linkedin.com/jobs/view/123456"))  # True
