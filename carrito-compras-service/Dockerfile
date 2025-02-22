FROM openjdk:17-jdk-slim
ARG JAR_FILE=tarjet/carrito-compras-service-0.0.1.jar
COPY ${JAR_FILE} app_carrito-compras-service-0.0.1.jar
ENTRYPOINT{"java", "-jar", "app_carrito-compras-service-0.0.1.jar"}