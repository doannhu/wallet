# Step 1: Use a Maven image to build the Spring Boot app
FROM maven:3.8.4-openjdk-17 AS build

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the pom.xml and source code into the working directory
COPY pom.xml ./
COPY src ./src

# Step 4: Build the application
RUN mvn clean package -DskipTests

# Step 5: Use a lightweight OpenJDK runtime image for running the app
FROM openjdk:17-jdk-slim

# Step 6: Set the working directory for the runtime image
WORKDIR /app

# Step 7: Copy the built .jar file from the build image to the runtime image
COPY --from=build /app/target/*.jar /app/mini-wallet.jar

# Step 8: Expose the port that your application will run on
EXPOSE 8080

# Step 9: Define the entry point for the container
ENTRYPOINT ["java", "-jar", "/app/mini-wallet.jar"]
