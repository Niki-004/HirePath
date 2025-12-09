import csv
from collections import Counter
from pathlib import Path


def load_jobs(csv_path: str):
    """
    Expects a CSV with at least a 'status' column.
    Example headers: id,company,position,status,applied_date,notes
    """
    jobs = []
    path = Path(csv_path)

    if not path.exists():
        raise FileNotFoundError(f"File not found: {csv_path}")

    with path.open(mode="r", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            jobs.append(row)

    return jobs


def compute_status_stats(jobs):
    statuses = [job.get("status", "").strip().upper() or "UNKNOWN" for job in jobs]
    return Counter(statuses)


def print_report(jobs):
    total = len(jobs)
    status_counts = compute_status_stats(jobs)

    print("==== Job Application Stats ====")
    print(f"Total applications: {total}")
    print()

    for status, count in status_counts.items():
        percent = (count / total * 100) if total > 0 else 0
        print(f"{status:10s} : {count:3d} ({percent:5.1f}%)")


if __name__ == "__main__":
    import sys

    if len(sys.argv) < 2:
        print("Usage: python job_application_stats.py path/to/jobs.csv")
        sys.exit(1)

    csv_file = sys.argv[1]
    jobs = load_jobs(csv_file)
    print_report(jobs)
