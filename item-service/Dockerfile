FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/item-service.jar item-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "item-service.jar"]
