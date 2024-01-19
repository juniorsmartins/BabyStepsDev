ARG DIRETORIO_MICRO=microusuarios

FROM amazoncorretto:21.0.1-al2023-headful AS builder

LABEL authors="juniormartins"

ARG DIRETORIO_MICRO
WORKDIR /app/$DIRETORIO_MICRO

COPY ./pom.xml /app
COPY ./$DIRETORIO_MICRO/.mvn ./.mvn
COPY ./$DIRETORIO_MICRO/mvnw .
COPY ./$DIRETORIO_MICRO/pom.xml .

# RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline

COPY ./$DIRETORIO_MICRO/src ./src

RUN ./mvnw clean package -DskipTests


FROM amazoncorretto:21.0.1-al2023-headful

ARG DIRETORIO_MICRO
WORKDIR /app

# add logging.file.path=/app/logs no application.properties
RUN mkdir ./logs

ARG DIRETORIO_TARGET=/app/$DIRETORIO_MICRO/target

COPY --from=builder $DIRETORIO_TARGET/microusuarios-0.0.1-SNAPSHOT.jar .

#ARG PORT_APP=8001
#ENV PORT $PORT_APP
#EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "microusuarios-0.0.1-SNAPSHOT.jar"]

