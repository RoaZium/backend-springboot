# Use OpenJDK 17 slim image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file from the build directory
COPY springboot-core-common/build/libs/*.jar app.jar

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=mssql
ENV DB_HOST=192.168.10.170
ENV DB_PORT=1433

# Expose the port the app runs on
EXPOSE 38080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]