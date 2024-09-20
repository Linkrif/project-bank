FROM openjdk:17-jdk-alpine
MAINTAINER Sam
EXPOSE 8080
COPY build/libs/projectBank-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


