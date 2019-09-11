FROM openjdk:8-jdk-alpine
MAINTAINER "Szymon Gierszewski"
ENV TZ=Europe/Warsaw
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]