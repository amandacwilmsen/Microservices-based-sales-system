FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/vendas-microservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

HEALTHCHECK --interval=30s --timeout=10s --retries=5 CMD wget --quiet --spider http://localhost:8083/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
