FROM openjdk:21-jdk
WORKDIR /app
COPY target/config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=native", "/app/config-server.jar"]
