#!/usr/bin/env bash

# Gradle Wrapper script for Unix-like systems
# This script will run Gradle tasks

set -e

# Get the script directory
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Check if gradle is available
if command -v gradle &> /dev/null; then
    gradle "$@"
elif [ -f "$SCRIPT_DIR/gradlew" ]; then
    bash "$SCRIPT_DIR/gradlew" "$@"
else
    echo "Gradle not found. Please install Gradle or use ./gradlew"
    exit 1
fi

