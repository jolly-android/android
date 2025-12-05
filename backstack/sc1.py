import os
import random
import subprocess
from datetime import datetime, timedelta

NAME = "Jolly Jindal"
EMAIL = "coolart25@gmail.com"

# Target commit frequency: every 5–10 days
MIN_GAP = 5
MAX_GAP = 10

# Natural, meaningful commit message templates
TEMPLATES = [
    "Refactor {}",
    "Improve {} logic",
    "Fix issue in {}",
    "Clean up formatting in {}",
    "Optimize {} performance",
    "Minor update to {}",
    "Improve readability in {}",
    "Add missing comments to {}",
    "Code cleanup in {}",
    "Enhance {} implementation"
]

def run(cmd):
    return subprocess.check_output(cmd, shell=True).decode("utf-8")

def choose_message(file_path):
    base = os.path.basename(file_path)
    template = random.choice(TEMPLATES)
    return template.format(base)

def random_edit_file():
    files = []
    for root, dirs, filenames in os.walk("."):
        for f in filenames:
            if not f.startswith("."):  # skip hidden files
                files.append(os.path.join(root, f))

    if not files:
        return None

    target = random.choice(files)

    # Write a harmless comment without any timestamp
    with open(target, "a") as f:
        f.write("\n# minor internal update\n")

    return target

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

        run("git add .")

        # Correct format — your previous script broke mid-l

