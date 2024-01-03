FROM openjdk:21.0.1 AS builder

WORKDIR /app

COPY ./target/micronoticias-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "micronoticias-0.0.1-SNAPSHOT.jar"]

