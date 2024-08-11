FROM openjdk:21-jdk
WORKDIR /app
COPY target/eureka-0.0.1-SNAPSHOT.jar /app/eureka.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local", "/app/eureka.jar"]
