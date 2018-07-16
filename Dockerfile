FROM openjdk:8-alpine

MAINTAINER Deyve Vieira <deyvedvm@hotmail.com>

RUN apk update && apk add bash

RUN mkdir -p /opt/app

ENV PROJECT_HOME /opt/app

COPY target/eiti-crud.jar $PROJECT_HOME/eiti-crud.jar

WORKDIR $PROJECT_HOME

CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./eiti-crud.jar"]


