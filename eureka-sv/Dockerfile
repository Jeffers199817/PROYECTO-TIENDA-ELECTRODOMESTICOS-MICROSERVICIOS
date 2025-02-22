FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y netcat-openbsd
ARG  JAR_FILE=target/eureka-sv-0.0.1.jar
COPY ${JAR_FILE} app_eureka.jar
ENTRYPOINT ["java", "-jar","app_eureka.jar"]