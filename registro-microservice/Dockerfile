FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
VOLUME /tmp
COPY --from=build /app/target/registro-microservice-1.0.0.jar registro-microservice.jar
HEALTHCHECK --interval=30s --timeout=10s --retries=5 CMD wget --quiet --spider http://localhost:8084/actuator/health || exit 1
EXPOSE 8084
ENTRYPOINT ["java","-jar","/registro-microservice.jar"]