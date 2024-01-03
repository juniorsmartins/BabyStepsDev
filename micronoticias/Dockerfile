FROM amazoncorretto:21.0.1-al2023-headful AS builder

LABEL authors="juniormartins"

WORKDIR /app/micronoticias

COPY ./pom.xml /app
COPY ./micronoticias/.mvn ./.mvn
COPY ./micronoticias/mvnw .
COPY ./micronoticias/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
# RUN ./mvnw dependency:go-offline

COPY ./micronoticias/src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "./target/micronoticias-0.0.1-SNAPSHOT.jar"]

