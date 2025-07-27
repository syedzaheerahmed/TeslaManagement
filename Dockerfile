# syntax=docker/dockerfile:1

# ===================================================================
# STAGE 1: Build the Application using Maven
# This stage uses a full JDK and Maven image to build the JAR file.
# ===================================================================
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build

# Copy the pom.xml and download dependencies first to leverage Docker layer caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
# Use -DskipTests to speed up the build process in the pipeline
RUN mvn clean package -DskipTests


# ===================================================================
# STAGE 2: Create the Final, Optimized Runtime Image
# This stage starts from a minimal JRE-only image for security and size.
# ===================================================================
FROM eclipse-temurin:17-jre-jammy

# Create a non-root user for security
ARG UID=10001
RUN addgroup --system --gid ${UID} appgroup && \
    adduser --system --uid ${UID} --ingroup appgroup --shell /bin/sh appuser

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
# We are copying the single fat JAR, which is simpler than layertools
# for many GCP deployments and works perfectly with our setup.
COPY --from=builder /build/target/TeslaManagement-*.jar app.jar

# Set ownership to the non-root user
RUN chown appuser:appgroup app.jar

# Switch to the non-root user
USER appuser

# Set the active Spring profile to 'prod'
# This ensures our production configuration is always used for this image.
ENV SPRING_PROFILES_ACTIVE=prod

# Set default JVM options for running in a container
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Expose the port the application runs on
EXPOSE 8080

# The simplified entrypoint.
# We use 'exec' form to ensure signals are handled correctly.
# The JAVA_OPTS and Spring profile are automatically used.
ENTRYPOINT ["java", "-jar", "app.jar"]