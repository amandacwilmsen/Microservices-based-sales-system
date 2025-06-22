FROM eclipse-temurin:21-jre
VOLUME /tmp
COPY target/service-registry-1.0.0.jar service-registry.jar
ENTRYPOINT ["java","-jar","/service-registry.jar"]