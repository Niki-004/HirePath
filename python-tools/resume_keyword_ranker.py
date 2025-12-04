# Day 9 – Resume Keyword Ranker

import re

def rank_keywords(resume_text, keywords):
    results = {}

    for word in keywords:
        pattern = rf"\b{re.escape(word)}\b"
        matches = re.findall(pattern, resume_text, flags=re.IGNORECASE)
        results[word] = len(matches)

    return results


if __name__ == "__main__":
    resume = """
    Experienced in Java, Spring Boot, REST APIs, SQL, Git, and cloud deployment.
    Worked with Python and automated pipelines.
    """

    target_keywords = ["Java", "Python", "Spring", "SQL", "AWS"]

    print("Keyword Ranking:")
    print(rank_keywords(resume, target_keywords))
