import csv
from collections import Counter

def summarize_job_status(csv_file_path):
    """
    Reads a CSV file of job applications and prints a summary by status.
    Expected columns: company, position, status
    """

    status_counter = Counter()

    with open(csv_file_path, newline='', encoding='utf-8') as file:
        reader = csv.DictReader(file)

        for row in reader:
            status = row.get("status", "").upper()
            if status:
                status_counter[status] += 1

    print("Job Application Status Summary:")
    for status, count in status_counter.items():
        print(f"{status}: {count}")


if __name__ == "__main__":
    # Example usage
    summarize_job_status("jobs.csv")
