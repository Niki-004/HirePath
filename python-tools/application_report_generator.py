import csv
from collections import Counter

def generate_report(csv_path):
    with open(csv_path, "r") as file:
        reader = csv.DictReader(file)
        companies = []
        positions = []
        statuses = []

        for row in reader:
            companies.append(row["company"])
            positions.append(row["position"])
            statuses.append(row["status"])

    return {
        "top_company": Counter(companies).most_common(1),
        "top_position": Counter(positions).most_common(1),
        "status_breakdown": Counter(statuses)
    }

if __name__ == "__main__":
    report = generate_report("applications.csv")
    print(report)
