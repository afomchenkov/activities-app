#!/bin/bash

# Exit on error
set -e

# Handle Ctrl+C (SIGINT)
cleanup() {
    cd ..
    echo ""
    echo "Stopping Docker Compose services..."
    docker compose -f ./docker-compose.yml down
    exit 0
}

# Trap Ctrl+C (SIGINT) and call cleanup function
trap cleanup SIGINT

# Step 1: Build and start Docker containers
echo "Starting Docker containers with Docker Compose..."
echo "Waiting for server to be ready..."

docker compose -f ./docker-compose.yml up --build --remove-orphans -d

# Step 2: Wait for Docker containers to be fully up
echo "Waiting for containers to be up..."
until docker compose -f ./docker-compose.yml exec -T spring-boot-app curl -s http://localhost:8080/graphiql > /dev/null; do
    echo "Waiting for app stack to be available..."
    sleep 10
done

echo "App stack is now available."