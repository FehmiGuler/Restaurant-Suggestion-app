# Use an OpenJDK base image
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/*.jar /app/restaurant_service.jar

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run your application
ENTRYPOINT ["java", "-jar", "restaurant_service.jar", "--host", "0.0.0.0", "--port", "8080"]
