#!/usr/bin/env sh
set -eu

history_dir=${1:?history directory is required}
revision_count=${2:?revision count is required}
war_file=${3:?WAR file path is required}
project_name=${4:?project name is required}

root=$(pwd)
team_dir="$root/$history_dir/team"
worktrees_dir="$team_dir/worktrees"
wars_dir="$team_dir/wars"
logs_dir="$team_dir/logs"

cleanup_worktrees() {
    for worktree in "$worktrees_dir"/*; do
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

cleanup_worktrees
rm -rf "$worktrees_dir" "$wars_dir" "$logs_dir"
mkdir -p "$worktrees_dir" "$wars_dir" "$logs_dir"

for revision in $(git rev-list --max-count="$revision_count" --skip=1 HEAD); do
    worktree="$worktrees_dir/$revision"
    log_file="$logs_dir/$revision.log"

    git worktree add "$worktree" "$revision"
    prepare_build_files "$worktree"

    if (cd "$worktree" && ant build) > "$log_file" 2>&1; then
        cp "$worktree/$war_file" "$wars_dir/$project_name-$revision.war"
        echo "Built $revision"
    else
        echo "Skipped $revision, see $log_file"
    fi

    git worktree remove "$worktree" --force
done

test -n "$(find "$wars_dir" -name "*.war" -print -quit)"
