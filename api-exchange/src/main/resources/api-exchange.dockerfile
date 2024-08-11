FROM openjdk:21-jdk
WORKDIR /app
COPY target/api-exchange-0.0.1-SNAPSHOT.jar /app/api-exchange.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "/app/api-exchange.jar"]
