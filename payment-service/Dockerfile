FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/payment-service.jar payment-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "payment-service.jar"]
