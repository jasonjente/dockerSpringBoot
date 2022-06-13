#!/bin/bash

echo "Inspecting Database dependencies: postgres"
docker inspect postgres > /dev/null 2>&1
if	[ "$?" != "0" ]; then
    cd "./database"
    echo "Images not found building them."
    echo "Building image postgres-db (base image from alpine distro.)"
    docker build -t postgres-db -f postgres.Dockerfile .
    echo "Extending image postgres-db -> postgres (base image from alpine distro.)"
    docker build -t postgres .
    echo "Removing postgres-db base image."
    docker rmi postgres-db
    cd ..
fi;

echo "Inspecting springboot-app image: "
docker inspect springboot-app > /dev/null 2>&1
if	[ "$?" != "0" ]; then
    cd ./application
    docker build --build-arg JAR_FILE=/sprinbgoot/target/SimpleApplication-1.0.0.0.jar -t sprinboot-app .
    cd ..
fi;

echo "Building app:"
docker-compose -f docker-compose.yaml build --no-cache 
echo "Running app:"
docker-compose -f docker-compose.yaml up