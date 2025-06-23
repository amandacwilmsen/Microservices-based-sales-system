FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
VOLUME /tmp
COPY --from=build /app/target/api-gateway-1.0.0.jar api-gateway.jar
HEALTHCHECK --interval=30s --timeout=10s --retries=5 CMD wget --quiet --spider http://localhost:8080/actuator/health || exit 1
EXPOSE 8080
ENTRYPOINT ["java","-jar","/api-gateway.jar"]