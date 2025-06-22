FROM eclipse-temurin:21-jre
VOLUME /tmp
COPY target/api-gateway-1.0.0.jar api-gateway.jar
ENTRYPOINT ["java","-jar","/api-gateway.jar"]