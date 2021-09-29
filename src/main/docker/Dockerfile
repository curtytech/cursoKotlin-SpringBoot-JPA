FROM openjdk:8
MAINTAINER leogloria
WORKDIR /opt/app

ARG JAR_FILE=target/tour-0.0.1-SNAPSHOT.jar

COPY maven/ /opt/app
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT  "ls" && /bin/bash
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]