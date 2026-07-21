# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml first to cache dependencies
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the generated JAR
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
