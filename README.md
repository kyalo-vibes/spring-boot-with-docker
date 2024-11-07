# Demo App Containerization

This project demonstrates the containerization of a Spring Boot demo application using Docker.

## Table of Contents

* [Prerequisites](#prerequisites)
* [Containerization](#containerization)
* [Docker Compose](#docker-compose)
* [Running the Application](#running-the-application)

## Prerequisites

* Docker installed on your machine
* Docker Compose installed on your machine
* Java 17 (or later) installed on your machine

## Containerization

The demo application is containerized using Docker. The `Dockerfile` is used to create a Docker image for the application.

```dockerfile
FROM openjdk:17-jdk

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

EXPOSE 8080

CMD ["java", "-jar", "demo.jar"]
```

This Dockerfile uses the official OpenJDK 17 image as the base image, sets up the working directory, copies the demo application JAR file, exposes port 8080, and sets the default command to run the JAR file.

## Docker Compose
The `docker-compose.yml` file is used to define and run the Docker containers.

### docker-compose.yml
```yaml
# docker-compose.yml
services:
  postgres:
    container_name: kyalo-postgres
    image: postgres
    environment:
      POSTGRES_USER: username
      POSTGRES_PASSWORD: password
      POSTGRES_DB: demo_db
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - spring-boot-network

  spring-boot-app:
    container_name: demo-app
    image: spring/demo
    ports:
      - "8088:8080"
    networks:
      - spring-boot-network
    depends_on:
      - postgres

volumes:
  postgres_data:

networks:
  spring-boot-network:
    driver: bridge
```

This `docker-compose.yml` file defines two services: `postgres` and `spring-boot-app`. The `postgres` service uses the official Postgres image and sets up the environment variables, ports, and volumes. The `spring-boot-app` service uses the `spring/demo` image and sets up the ports, networks, and dependencies.

## Running the Application
To run the application, navigate to the project directory and run the following command:

```bash
docker-compose up
```

This command will start the containers and make the application available at [http://localhost:8088](http://localhost:8088).
