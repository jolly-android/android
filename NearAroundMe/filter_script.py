
import os, random, subprocess

TEMPLATES = ['Refactor {}', 'Improve {} logic', 'Fix issue in {}', 'Clean up formatting in {}', 'Optimize {}', 'Minor update to {}', 'Improve readability in {}', 'Add missing comments to {}', 'Code cleanup in {}', 'Enhance {} implementation']

def pick_message(path):
    import os, random
    base = os.path.basename(path)
    return random.choice(TEMPLATES).format(base)

def modify_file(commit):
    candidates = []
    for root, dirs, files in os.walk('.'):
        dirs[:] = [d for d in dirs if not d.startswith('.')]
        for f in files:
            if not f.startswith('.'):
                candidates.append(os.path.join(root, f))
    if not candidates:
        return None

    chosen = random.choice(candidates)
    with open(chosen, "a") as f:
        f.write("\n# small internal update\n")

    return chosen

def rewrite_message(commit):
    changed = modify_file(commit)
    if changed:
        commit.message = pick_message(changed).encode("utf-8")

