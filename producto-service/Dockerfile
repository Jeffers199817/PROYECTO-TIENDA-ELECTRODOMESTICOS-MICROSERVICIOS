FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/producto-service-0.0.1.jar
COPY ${JAR_FILE} app_producto-service-0.0.1.jar
ENTRYPOINT ["java","-jar","app_producto-service-0.0.1.jar"]