#BUILD STAGE
FROM openjdk:15-jdk-alpine as build
WORKDIR /workspace/app

EXPOSE 7078

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests -e
WORKDIR /workspace/app/target/dependency
RUN jar -xf ../*.jar



#PROD STAGE
FROM openjdk:15-jdk-alpine as prod

# Don't run as root, create new user here
RUN addgroup -S demo && adduser -S demo -G demo
VOLUME /tmp
USER demo

# Copy over unpacked jar content from build stage
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-noverify","-XX:TieredStopAtLevel=1","-cp","app:app/lib/*","-Dspring.main.lazy-initialization=true","flowcontrol.article.ArticleApplication"]

MAINTAINER Rik Peeters