FROM amazoncorretto:21.0.1-al2023-headful AS builder

LABEL authors="juniormartins"

WORKDIR /app/microusuarios

COPY ./pom.xml /app
COPY ./microusuarios/.mvn ./.mvn
COPY ./microusuarios/mvnw .
COPY ./microusuarios/pom.xml .

# RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline

COPY ./microusuarios/src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "./target/microusuarios-0.0.1-SNAPSHOT.jar"]