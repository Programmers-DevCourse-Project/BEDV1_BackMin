FROM openjdk:14-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} backmin-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/backmin-0.0.1.jar"]