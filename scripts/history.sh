#!/usr/bin/env sh
set -eu

history_dir=${1:?history directory is required}
diff_file=${2:?diff file path is required}

root=$(pwd)
search_dir="$root/$history_dir/search"
logs_dir="$root/$history_dir/logs"
diff_path="$root/$diff_file"

cleanup_worktrees() {
    for worktree in "$search_dir"/*; do
        [ -d "$worktree" ] && git worktree remove "$worktree" --force || true
    done
    git worktree prune
}

prepare_build_files() {
    worktree=$1

    cp "$root/build.xml" "$root/build.properties" "$worktree/"
    mkdir -p "$worktree/manifest" "$worktree/lib"
    cp "$root/manifest/MANIFEST.MF" "$worktree/manifest/"
    cp "$root"/lib/*.jar "$worktree/lib/"
}

mkdir -p "$root/$history_dir"

if ant compile > "$root/$history_dir/current-compile.log" 2>&1; then
    echo "Current revision compiles. History search is not needed."
    exit 0
fi

cleanup_worktrees
rm -rf "$search_dir" "$logs_dir"
mkdir -p "$search_dir" "$logs_dir"

previous_revision=
last_compilable_revision=
first_broken_revision=

for revision in $(git rev-list HEAD); do
    worktree="$search_dir/$revision"
    log_file="$logs_dir/$revision.log"

    git worktree add "$worktree" "$revision" > /dev/null 2>&1
    prepare_build_files "$worktree"

    if (cd "$worktree" && ant compile) > "$log_file" 2>&1; then
        last_compilable_revision=$revision
        first_broken_revision=$previous_revision
        git worktree remove "$worktree" --force > /dev/null 2>&1
        break
    fi

    git worktree remove "$worktree" --force > /dev/null 2>&1
    previous_revision=$revision
done

if [ -z "$last_compilable_revision" ]; then
    echo "No compilable revision found"
    exit 1
fi

if [ -z "$first_broken_revision" ]; then
    git diff > "$diff_path"
    echo "Committed HEAD compiles, but current working tree does not."
    echo "Working tree diff saved to $diff_file"
    exit 0
fi

git diff "$last_compilable_revision" "$first_broken_revision" > "$diff_path"
echo "Last compilable revision: $last_compilable_revision"
echo "First broken revision after it: $first_broken_revision"
echo "Diff saved to $diff_file"
