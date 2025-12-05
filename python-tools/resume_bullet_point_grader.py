import re

ACTION_VERBS = [
    "built", "developed", "designed", "improved", "led", "implemented",
    "created", "optimized", "launched", "managed", "automated"
]

TECH_KEYWORDS = [
    "java", "python", "sql", "spring boot", "docker", "api", "microservice",
    "react", "aws", "git"
]

def grade_bullet_point(text: str) -> dict:
    """Grades a resume bullet point on 3 metrics."""

    score = 0
    text_lower = text.lower()

    # 1. Check for strong action verb
    has_action = any(text_lower.startswith(v) for v in ACTION_VERBS)
    if has_action:
        score += 1

    # 2. Check if it contains measurable metrics
    has_metrics = bool(re.search(r"\d+%|\d+\$|\d+", text))
    if has_metrics:
        score += 1

    # 3. Check use of technical keywords
    has_keyword = any(k in text_lower for k in TECH_KEYWORDS)
    if has_keyword:
        score += 1

    return {
        "text": text,
        "action_verb": has_action,
        "metrics_used": has_metrics,
        "tech_keyword": has_keyword,
        "score": score,
        "rating": ["Weak", "Average", "Strong", "Excellent"][score]
    }


if __name__ == "__main__":
    example = "Built a Spring Boot API that reduced backend latency by 30%"
    result = grade_bullet_point(example)
    print(result)

