FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
VOLUME /tmp
COPY --from=build /app/target/service-registry-1.0.0.jar service-registry.jar
HEALTHCHECK --interval=30s --timeout=10s --retries=5 CMD wget --quiet --spider http://localhost:8761/actuator/health || exit 1
EXPOSE 8761
ENTRYPOINT ["java","-jar","/service-registry.jar"]