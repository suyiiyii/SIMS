FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY pom.xml ./pom.xml

COPY src ./src

RUN --mount=type=cache,target=/root/.m2 \
    mvn package  -am -DskipTests

RUN mkdir -p /layers && \
    cp /app/target/SIMS-0.0.1-SNAPSHOT.jar /layers/target.jar && \
    cd /layers && \
    java -Djarmode=layertools -jar /layers/target.jar extract

FROM eclipse-temurin:17-jre AS runtime

WORKDIR /app

COPY --from=build /layers/dependencies/ .
COPY --from=build /layers/snapshot-dependencies/ .
COPY --from=build /layers/spring-boot-loader/ .
COPY --from=build /layers/application/ .

EXPOSE 8080

# 执行命令
ENTRYPOINT [ "java",  "org.springframework.boot.loader.JarLauncher" ]
