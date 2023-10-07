FROM openjdk:17-jdk-alpine
MAINTAINER Vladislav Matienko
COPY target/pastebox-testwork-0.0.1-SNAPSHOT.jar pastebox-testwork.jar
ENTRYPOINT ["java", "-jar", "/pastebox-testwork.jar"]