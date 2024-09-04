# Use OpenJDK 17 slim image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file from the build directory
COPY build/libs/*.jar app.jar

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=mssql
ENV DB_HOST=192.168.50.155
ENV DB_PORT=1433
ENV DB_NAME=dms_db
ENV DB_USERNAME=sa
ENV DB_PASSWORD=1

# Expose the port the app runs on
EXPOSE 38080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]