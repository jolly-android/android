import os
import random
import subprocess
from datetime import datetime, timedelta

NAME = "Jolly Jindal"
EMAIL = "coolart25@gmail.com"

# Target commit frequency: every 5–10 days
MIN_GAP = 5
MAX_GAP = 10

def run(cmd):
    return subprocess.check_output(cmd, shell=True).decode("utf-8")

def random_edit_file():
    files = []
    for root, dirs, filenames in os.walk("."):
        for f in filenames:
            if not f.startswith("."):  # skip hidden files
                files.append(os.path.join(root, f))

    if not files:
        return None

    target = random.choice(files)
    with open(target, "a") as f:
        f.write(f"\n# auto-edit {datetime.now()}\n")

    return target

def random_date(start_date, end_date):
    delta = end_date - start_date
    return start_date + timedelta(days=random.randint(0, delta.days))

def generate_dates():
    dates = []
    end = datetime.now()
    current = end - timedelta(days=365)

    while current < end:
        gap = random.randint(MIN_GAP, MAX_GAP)
        current += timedelta(days=gap)
        if current < end:
            dates.append(current)

    return dates

def main():
    dates = generate_dates()

    for d in dates:
        changed_file = random_edit_file()
        if not changed_file:
            print("No files found!")
            return

        run(f'git add .')

        formatted_date = d.strftime("%Y-%m-%dT12:00:00")

        env = f'GIT_AUTHOR_DATE="{formatted_date}" GIT_COMMITTER_DATE="{formatted_date}"'
        commit_cmd = f'{env} git commit -m "Auto commit on {formatted_date}" --author="{NAME} <{EMAIL}>"'

        print("Running:", commit_cmd)
        run(commit_cmd)

    print("\nAll commits created! Now run:")
    print("git push --force")

if __name__ == "__main__":
    main()

