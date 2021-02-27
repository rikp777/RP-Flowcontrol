#FROM ubuntu:20.10
#
#EXPOSE 7073
#
#COPY ./target/production-0.0.1-SNAPSHOT.jar /opt
#RUN apt update -y \
#    && apt install -y openjdk-15-jdk
#
#ENTRYPOINT java -jar /opt/production-0.0.1-SNAPSHOT.jar -Djava.net.preferIPv4Stack=true


# syntax=docker/dockerfile:experimental
FROM openjdk:15-jdk-alpine as build
WORKDIR /workspace/app

EXPOSE 7073

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests -e
WORKDIR /workspace/app/target/dependency
RUN jar -xf ../*.jar

FROM openjdk:15-jdk-alpine
RUN addgroup -S demo && adduser -S demo -G demo
VOLUME /tmp
USER demo
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-noverify","-XX:TieredStopAtLevel=1","-cp","app:app/lib/*","-Dspring.main.lazy-initialization=true","flowcontrol.production.ProductionApplication"]
