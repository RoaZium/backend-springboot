# Use OpenJDK 17 slim image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file from the build directory
COPY build/libs/*.jar app.jar

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=mariadb
ENV DB_HOST=127.0.0.1
ENV DB_PORT=3306
ENV DB_NAME=ams_db
ENV DB_USERNAME=root
ENV DB_PASSWORD=1

# Expose the port the app runs on
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]