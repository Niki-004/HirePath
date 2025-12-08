import re

TECH_SKILLS = [
    "java", "python", "spring", "spring boot", "sql", "postgresql",
    "docker", "git", "react", "javascript", "html", "css",
    "aws", "azure", "gcp", "kubernetes"
]

def extract_skills(text: str):
    found = []

    lower_text = text.lower()

    for skill in TECH_SKILLS:
        pattern = r"\b" + re.escape(skill) + r"\b"
        if re.search(pattern, lower_text):
            found.append(skill)

    return sorted(set(found))

if __name__ == "__main__":
    sample_resume = """
    Experienced Java + Spring Boot developer with SQL and Docker knowledge.
    Worked with AWS and Kubernetes in cloud environments.
    """

    print("Detected skills:", extract_skills(sample_resume))

