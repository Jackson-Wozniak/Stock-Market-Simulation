#FROM maven:3.8.3-openjdk-17
#WORKDIR /app
#ADD target/*.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar", "--spring.config.name=application-docker"]

FROM maven:3.8.3-openjdk-17-slim AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/stock-market-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar","/app/app.jar", "--spring.config.name=application-docker"]