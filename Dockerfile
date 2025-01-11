FROM eclipse-temurin:17-jre-jammy

RUN mkdir /app

WORKDIR /app

ADD ./api/target/api-1.0-SNAPSHOT /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api-1.0-SNAPSHOT.jar"]