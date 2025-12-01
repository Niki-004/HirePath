# Multi-stage build for JobTracker backend

# ---------- 1) Build stage ----------
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle

# Make sure the wrapper is executable
RUN chmod +x ./gradlew

# Copy source code
COPY src ./src

# Build the Spring Boot jar
RUN ./gradlew bootJar --no-daemon

# ---------- 2) Runtime stage ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/build/libs/*SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]

