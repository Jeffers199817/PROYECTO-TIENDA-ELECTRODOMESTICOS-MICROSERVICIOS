FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/venta-service-0.0.1.jar
COPY ${JAR_FILE} app_venta-service-0.0.1.jar
ENTRYPOINT{"java","-jar","app_venta-service-0.0.1.jar"}
