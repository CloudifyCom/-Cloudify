FROM eclipse-temurin:17-jre-jammy

RUN mkdir /app

WORKDIR /app

ADD ./api/target/api-1.0-SNAPSHOT.jar /app/api-1.0-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/api-1.0-SNAPSHOT.jar"]
