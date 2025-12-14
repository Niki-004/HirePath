"""
Resume Keyword Ranker
---------------------
Scores how well a resume matches a job description
based on keyword frequency.
"""

import re
from collections import Counter

def clean_text(text: str) -> list[str]:
    text = text.lower()
    text = re.sub(r"[^a-z0-9\s]", "", text)
    return text.split()

def keyword_score(resume_text: str, job_description: str) -> dict:
    resume_words = clean_text(resume_text)
    jd_words = clean_text(job_description)

    resume_count = Counter(resume_words)
    jd_keywords = set(jd_words)

    matched = {
        word: resume_count[word]
        for word in jd_keywords
        if word in resume_count
    }

    score = sum(matched.values())

    return {
        "score": score,
        "matched_keywords": dict(sorted(matched.items(),
                                         key=lambda x: x[1],
                                         reverse=True))
    }

if __name__ == "__main__":
    resume = """
    Software engineering student with experience in Java,
    Spring Boot, SQL, Git, REST APIs, and basic Python.
    """

    job_desc = """
    Looking for a backend intern with Java, Spring Boot,
    REST APIs, SQL, and Git experience.
    """

    result = keyword_score(resume, job_desc)
    print("Score:", result["score"])
    print("Matched keywords:", result["matched_keywords"])
