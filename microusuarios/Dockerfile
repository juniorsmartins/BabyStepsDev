FROM amazoncorretto:21.0.1-al2023-headful AS builder

LABEL authors="juniormartins"

WORKDIR /app

COPY ./target/microusuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "microusuarios-0.0.1-SNAPSHOT.jar"]