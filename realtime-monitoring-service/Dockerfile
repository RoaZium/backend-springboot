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
ENV DB_NAME=dms_db
ENV DB_USERNAME=coever
ENV DB_PASSWORD=7N4F2<{mDs%7b3-.

# Expose the port the app runs on
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]