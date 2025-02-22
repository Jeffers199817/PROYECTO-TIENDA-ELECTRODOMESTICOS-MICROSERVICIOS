FROM openjdk:17-jdk-slim
ARG JAR_FILE=tarjet/config-server-0.0.1.jar
COPY ${JAR_FILE} app_config-server-0.0.1.jar

ENTRYPOINT ["java", "-jar", "app_config-server-0.0.1.jar"]