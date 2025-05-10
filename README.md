"# TeslaManagement" 
"# TeslaManagement" 
## Profiles
- `dev`: For local development with a local PostgreSQL database and debug logging.
    - Run: `mvn spring-boot:run -Dspring-boot.run.profiles=dev`
- `prod`: For production with environment variables for database and JWT secret.
    - Run: `java -jar your-app.jar --spring.profiles.active=prod`