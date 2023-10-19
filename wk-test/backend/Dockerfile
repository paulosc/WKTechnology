# Use the official OpenJDK image as a parent image
FROM openjdk:21-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the compiled JAR file into the container at /app
COPY build/libs/WKTest-0.0.1-SNAPSHOT.jar .

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java","-jar","WKTest-0.0.1-SNAPSHOT.jar"] CMD ["-start"]
