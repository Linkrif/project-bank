FROM gradle:8.10-jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-jdk-alpine
COPY --from=build /home/gradle/src/build/libs/*.jar/ projectbank.jar
ENTRYPOINT ["java","-jar","projectbank.jar"]