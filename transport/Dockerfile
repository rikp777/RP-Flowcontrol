# ____        _ _     _       _
#| __ ) _   _(_) | __| |  ___| |_ __ _  __ _  ___
#|  _ \| | | | | |/ _` | / __| __/ _` |/ _` |/ _ \
#| |_) | |_| | | | (_| | \__ \ || (_| | (_| |  __/
#|____/ \__,_|_|_|\__,_| |___/\__\__,_|\__, |\___|
#                                      |___/
# First we build the jar with maven in a fat image with all the tools.
FROM maven:3.6.3-openjdk-11 as build
LABEL maintainer="RIK PEETERS"
WORKDIR /app
COPY pom.xml .
# Install maven dependency packages, this takes about a minute.
RUN mvn dependency:go-offline
# Then we copy in all source and build splitting out dependencies and source
# will save time in re-builds.
COPY . .
RUN mvn -T 2C install -DskipTests
# Unpack fat jar.
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


# ____                   _
#|  _ \  _____   __  ___| |_ __ _  __ _  ___
#| | | |/ _ \ \ / / / __| __/ _` |/ _` |/ _ \
#| |_| |  __/\ V /  \__ \ || (_| | (_| |  __/
#|____/ \___| \_/   |___/\__\__,_|\__, |\___|
#                                 |___/
FROM build as development
CMD ["mvn", "spring-boot:run"]


# ____                _       _
#|  _ \ _ __ ___   __| |  ___| |_ __ _  __ _  ___
#| |_) | '__/ _ \ / _` | / __| __/ _` |/ _` |/ _ \
#|  __/| | | (_) | (_| | \__ \ || (_| | (_| |  __/
#|_|   |_|  \___/ \__,_| |___/\__\__,_|\__, |\___|
#                                      |___/
# This stage starts fresh with a minimal debian image and only copying over
# source code.
FROM openjdk:11-jre-slim as prod

# Don't run as root, create a new `java` user here.
RUN groupadd --gid 1000 java \
  && useradd --uid 1000 --gid java --shell /bin/bash --create-home java
USER java

# Copy over unpacked jar content from build stage.
ARG DEPENDENCY=/app/target/dependency
COPY --from=build --chown=java:java ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build --chown=java:java ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build --chown=java:java ${DEPENDENCY}/BOOT-INF/classes /app
CMD ["java","-cp","app:app/lib/*","-Dspring.devtools.restart.enabled=false", "flowcontrol.transport.TransportApplication"]

# Expose the port to access the service
EXPOSE 7072