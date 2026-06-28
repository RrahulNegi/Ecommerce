# --- STAGE 1: Build the Application ---
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /workspace/app

# Copy the wrapper and pom.xml first to leverage Docker cache for dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Package the application (skipping tests for faster build)
RUN ./mvnw install -DskipTests

# --- STAGE 2: Run the Application ---
FROM eclipse-temurin:17-jre
WORKDIR /workspace/app

# Copy the compiled artifact from the builder stage
COPY --from=builder /workspace/app/target/*.jar app.jar

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]