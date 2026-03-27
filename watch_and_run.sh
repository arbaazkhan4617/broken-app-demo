#!/bin/bash

# Navigate to the script's directory (spring-demo)
cd "$(dirname "$0")"

echo "==== Starting Spring Boot Live Auto-Runner ===="
echo "The app runs on port 8081."
echo "Press Ctrl+C to stop everything."

# Function to clean up the background server on exit
cleanup() {
    echo "Stopping Spring Boot..."
    if [ -n "$PID" ]; then
        kill $PID 2>/dev/null || true
    fi
    exit 0
}
trap cleanup SIGINT SIGTERM

echo "[*] Booting Spring Application for the first time..."
./mvnw spring-boot:run &
PID=$!

while true; do
    # Fetch latest from GitHub silently
    git fetch origin main -q 2>/dev/null || true
    
    LOCAL=$(git rev-parse HEAD)
    REMOTE=$(git rev-parse origin/main)

    if [ "$LOCAL" != "$REMOTE" ]; then
        echo ""
        echo "=============================================="
        echo "🚀 UPDATE DETECTED ON GITHUB! (PR Merged) 🚀"
        echo "=============================================="
        echo "[*] Pulling latest code..."
        git pull origin main --rebase
        
        echo "[*] Stopping vulnerable version..."
        kill $PID 2>/dev/null || true
        wait $PID 2>/dev/null || true
        
        echo "[*] Rebuilding and restarting..."
        ./mvnw spring-boot:run &
        PID=$!
    fi
    
    # Check every 3 seconds for extremely fast demo feedback
    sleep 3
done
