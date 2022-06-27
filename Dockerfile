FROM gradle:jdk17-alpine AS build

COPY . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon


FROM openjdk:17.0-jdk

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/hacking-adventures.jar /app/spring-boot-application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]
