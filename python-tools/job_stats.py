import json
import statistics
from datetime import datetime

def load_jobs(path):
    """Load job data from a JSON file exported from backend."""
    with open(path, "r") as f:
        return json.load(f)

def days_between(date_str):
    """Return days between today and the date string YYYY-MM-DD."""
    if not date_str:
        return None
    date = datetime.strptime(date_str, "%Y-%m-%d").date()
    return (datetime.today().date() - date).days

def compute_stats(jobs):
    status_count = {}
    days_list = []

    for job in jobs:
        # status frequency
        status = job.get("status", "UNKNOWN").upper()
        status_count[status] = status_count.get(status, 0) + 1

        # days since applied
        applied_date = job.get("appliedDate")
        d = days_between(applied_date)
        if d is not None:
            days_list.append(d)

    avg_days = statistics.mean(days_list) if days_list else 0

    return {
        "total_jobs": len(jobs),
        "status_count": status_count,
        "average_days_since_application": avg_days,
    }

if __name__ == "__main__":
    data = load_jobs("sample_jobs.json")
    stats = compute_stats(data)

    print("\n📊 Job Statistics")
    print("------------------")
    print("Total Jobs:", stats["total_jobs"])
    print("Status Counts:", stats["status_count"])
    print("Average Days Since Applied:", stats["average_days_since_application"])
