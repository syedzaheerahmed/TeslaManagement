# syntax=docker/dockerfile:1

# Stage 1: Cache Maven Dependencies
FROM maven:3.8.5-eclipse-temurin-17 AS deps
WORKDIR /build
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -DskipTests

# Stage 2: Build the Application
FROM deps AS package
COPY src src/
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests && \
    mv target/TeslaManagement-*.jar target/app.jar

# Stage 3: Extract JAR Layers
FROM package AS extract
WORKDIR /build
RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted

# Stage 4: Create Runtime Image
FROM eclipse-temurin:17-jre-jammy AS final
ARG UID=10001
RUN groupadd -r appuser && \
    useradd -r -g appuser -u ${UID} -s /sbin/nologin appuser
WORKDIR /app
COPY --from=extract --chown=appuser:appuser /build/target/extracted/dependencies/ ./
COPY --from=extract --chown=appuser:appuser /build/target/extracted/spring-boot-loader/ ./
COPY --from=extract --chown=appuser:appuser /build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract --chown=appuser:appuser /build/target/extracted/application/ ./
RUN mkdir -p /tmp && chown appuser:appuser /tmp
USER appuser
EXPOSE 8080
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+ExitOnOutOfMemoryError -XX:+UseG1GC"
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["sh", "-c", "export DB_PASSWORD=$(cat /run/secrets/db-password) && export JWT_SECRET=$(cat /run/secrets/jwt-secret) && java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]