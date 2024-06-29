# 依赖于哪一个基础镜像，此镜像打包了 maven3.5 和 Java8
FROM maven:3.5-jdk-8-alpine as builder

# Copy local code to the container image.容器的工作目录
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup. 此命令在启动 docker 容器的时候可以覆盖掉
CMD ["java","-jar","/app/target/user-center-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]