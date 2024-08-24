FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

# 给 maven 配置阿里云镜像
RUN --mount=type=cache,target=/root/.m2 \
    mkdir -p /root/.m2 \
    && echo "<settings>\
    <mirrors>\
    <mirror>\
    <id>gdutnic</id>\
    <name>gdutnic maven</name>\
    <url>http://mirrors.gdut.edu.cn/nexus/repository/maven-public/</url>\
    <mirrorOf>central</mirrorOf>\
    </mirror>\
   <mirror>\
    <id>alimaven</id>\
    <mirrorOf>central</mirrorOf>\
    <name>aliyun maven</name>\
    <url>https://maven.aliyun.com/repository/public</url>\
    </mirror>\
    </mirrors>\
    </settings>" > /root/.m2/settings.xml

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
